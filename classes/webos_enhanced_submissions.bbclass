# Copyright (c) 2012-2014 LG Electronics, Inc.
#
# webos_enhanced_submissions
#
# Parse a WEBOS_VERSION in the following format:
#
#    <component-version>-<enhanced-submission>
#
# where <enhanced-submission> is of the form:
#
#    <submission>_<40-character-revision-hash>[;branch=<branch>]
#
# setting WEBOS_COMPONENT_VERSION, WEBOS_SUBMISSION, WEBOS_GIT_PARAM_TAG,
# WEBOS_GIT_REPO_TAG, WEBOS_SRCREV, WEBOS_GIT_PARAM_BRANCH, SRCREV, and PV.
#
# The default tag name is the Open webOS convention for submission tags, i.e.,
# they are of the form:
#    submissions/<decimal-integer>
# or, when the component has been branched:
#    submissions/<decimal-integer>.<decimal-integer>
# where <decimal-integer> does not contain leading zeros. The default can be
# overridden by setting WEBOS_GIT_REPO_TAG.
#
# WEBOS_SUBMISSION '0' has special meaning of latest available revision
# you can use WEBOS_VERSION = "1.0.0-0_${AUTOREV}" to download always
# the latest available source.

inherit webos_submissions

def webos_enhsub_get_srcrev(d, webos_v):
    srcrev = webos_version_get_srcrev(webos_v)
    webos_submission = d.getVar('WEBOS_SUBMISSION', True)
    webos_git_repo_tag = d.getVar('WEBOS_GIT_REPO_TAG', True) or "submissions/%s" % webos_submission
    if srcrev == None:
        # If there no hyphen, that means there's no srcrev, return tag name
        return webos_git_repo_tag
    if webos_submission == '0':
        return ''
    if srcrev == False:
        file = d.getVar('FILE', True)
        bb.fatal(("%s: WEBOS_VERSION needs to end with _<revision-hash> where " +
                     "<revision-hash> is the 40-character SHA1 identifier of '%s' tag")
                     % (file, webos_git_repo_tag))
    return srcrev

# Set WEBOS_SRCREV to value from WEBOS_VERSION and then use it as initial value
# for SRCREV and WEBOS_GIT_PARAM_TAG. This way setting SRCREV in a recipe after
# inheriting this bbclass or from some config file with override will work and
# checkout right revision without triggering a failure once a sanity check is
# implemented.
WEBOS_SRCREV = "${@webos_enhsub_get_srcrev(d, '${WEBOS_VERSION}')}"
SRCREV = "${WEBOS_SRCREV}"
WEBOS_GIT_PARAM_TAG = "${SRCREV}"
WEBOS_GIT_PARAM_BRANCH = "${@webos_version_get_branch('${WEBOS_VERSION}')}"

# When WEBOS_SRCREV isn't SHA-1 show error
do_fetch[prefuncs] += "webos_enhsub_srcrev_sanity_check"

python webos_enhsub_srcrev_sanity_check() {
    webos_srcrev = d.getVar('WEBOS_SRCREV', True)
    webos_submission = d.getVar('WEBOS_SUBMISSION', True)
    if webos_submission == '0':
        webos_version = d.getVar('WEBOS_VERSION', True)
        pn = d.getVar('PN', True)
        file = d.getVar('FILE', True)
        msg = "WEBOS_VERSION '%s' for recipe '%s' (file '%s') contains submission 0, which indicates using AUTOREV and cannot be used in official builds." % (webos_version, pn, file)
        package_qa_handle_error("webos-enh-sub-error", msg, d)
    elif (len(webos_srcrev) != 40 or (False in [c in "abcdef0123456789" for c in webos_srcrev])):
        file = d.getVar('FILE', True)
        bb.error("%s: WEBOS_SRCREV needs to contain a SRCREV" % file)
}

# When both SRCREV and WEBOS_SUBMISSION are defined check that they correspond
# This only compares tag and revision in local checkout (without using git ls-remote)
# This check is executed only when do_fetch is executed, that means that if someone
# moves the tag in remote repository, we won't notice it until do_fetch is re-executed.
do_unpack[postfuncs] += "submission_sanity_check"
python submission_sanity_check() {
    src_uri = (d.getVar('SRC_URI', True) or "").split()
    if len(src_uri) == 0:
        return

    found_first = False
    workdir = d.getVar('WORKDIR', True)
    pn = d.getVar('PN', True)
    file = d.getVar('FILE', True)
    fetcher = bb.fetch.Fetch(src_uri, d)
    urldata = fetcher.ud
    for u in urldata:
        tag_param = urldata[u].parm['tag'] if 'tag' in urldata[u].parm else None
        if urldata[u].type == 'git' and tag_param: # and (len(tag_param) != 40 or (False in [c in "abcdef0123456789" for c in tag_param])):
            if found_first:
                msg = "webos_enhanced_submission bbclass doesn't support multiple git repos in SRC_URI used in recipe '%s' (file '%s')" % (pn, file)
                package_qa_handle_error("webos-enh-sub-warning", msg, d)
                break
            found_first = True
            destsuffix_param = urldata[u].parm['destsuffix'] if 'destsuffix' in urldata[u].parm else 'git'
            webos_version = d.getVar('WEBOS_VERSION', True)
            webos_srcrev = d.getVar('WEBOS_SRCREV', True)
            webos_git_repo_tag = d.getVar('WEBOS_GIT_REPO_TAG', True)
            webos_submission = d.getVar('WEBOS_SUBMISSION', True)
            default_webos_git_repo_tag = "submissions/%s" % webos_submission
            if not webos_srcrev:
                msg = "WEBOS_VERSION '%s' for recipe '%s' (file '%s') doesn't contain SRCREV" % (webos_version, pn, file)
                package_qa_handle_error("webos-enh-sub-error", msg, d)
            if not webos_git_repo_tag:
                webos_git_repo_tag = default_webos_git_repo_tag
            elif webos_git_repo_tag == default_webos_git_repo_tag:
                msg = "Don't set WEBOS_GIT_REPO_TAG when the component is using default scheme 'submissions/${WEBOS_SUBMISSION}' in recipe '%s' (file '%s')" % (pn, file)
                package_qa_handle_error("webos-enh-sub-error", msg, d)
            checkout = "%s/%s" % (workdir, destsuffix_param)

            # '0' in 'webos_submission' is used with AUTOREV -> so don't check AUTOREV against submissions/0 tag
            # and show non-fatal ERROR to make sure that it's not accidentally merged in master
            if webos_submission == '0':
                msg = "WEBOS_VERSION '%s' for recipe '%s' (file '%s') contains submission 0, which indicates using AUTOREV" % (webos_version, pn, file)
                package_qa_handle_error("webos-enh-sub-error", msg, d)
            elif webos_git_repo_tag and webos_srcrev:
                bb.debug(2, "sanity check for pn '%s', tag_param '%s', webos_srcrev '%s', webos_git_repo_tag '%s', checkout '%s'" % (pn, tag_param, webos_srcrev, webos_git_repo_tag, checkout))
                cmd = "cd %s && git tag -l 2>/dev/null | grep '^%s$' | wc -l" % (checkout, webos_git_repo_tag)
                tag_exists = bb.fetch.runfetchcmd(cmd, d).strip()
                if tag_exists != "1":
                    msg = "The tag '%s' for recipe '%s' (file '%s') doesn't exist in local checkout of revision '%s'. It's possible that the tag already exists in a remote repository, but your local checkout (or checkout downloaded as a tarball from PREMIRROR) contains the requested revision without a tag assigned to it (this cannot happen with annotated tags, because they have their own SHA-1 which either exists or not). Please update your checkout in downloads/git2/... by executing git fetch --tags and run again." % (webos_git_repo_tag, pn, file, webos_srcrev)
                    package_qa_handle_error("webos-enh-sub-error", msg, d)
                    continue
                # for annotated tags there are 2 revisions and we don't care which one is used (same source)
                # $ git show-ref -d --tags 0.5
                #   70fb05fd340ab342c5132dc8bfa174dbe6c9d330 refs/tags/0.5
                #   215f9c884d0139c93feea940d255dc3575678218 refs/tags/0.5^{}
                # prefix with 'refs/tags/' so that partial tags aren't matched, e.g. librolegen:
                # $ git show-ref -d --tags 18
                #   cbedc69733f65cd2f498787a621c014e219d38ab refs/tags/submissions/18
                #   9040954a24115b05219e7dd459dcf91ad05cc739 refs/tags/submissions/18^{}
                # $ git show-ref -d --tags refs/tags/18
                #   <nothing>
                cmd = "cd %s && git show-ref -d --tags refs/tags/%s" % (checkout, webos_git_repo_tag)
                tag_srcrevs = bb.fetch.runfetchcmd(cmd, d).strip().split('\n')
                found_srcrev = False
                if len(tag_srcrevs) > 2:
                    msg = "The reference refs/tags/%s is matching more than 2 entries for recipe '%s' (file '%s'):\n%s" % (webos_git_repo_tag, pn, file, '\n'.join(tag_srcrevs))
                    package_qa_handle_error("webos-enh-sub-error", msg, d)
                if len(tag_srcrevs) == 1:
                    msg = "The tag '%s' for recipe '%s' (file '%s') is lightweight tag, please use annotated tag in next submission" % (webos_git_repo_tag, pn, file)
                    package_qa_handle_error("webos-enh-sub-error", msg, d)
                for tag_srcrev in tag_srcrevs:
                    (srcrev, name) = tag_srcrev.split()
                    if srcrev == webos_srcrev:
                        found_srcrev = True
                        if tag_srcrev != tag_srcrevs[0] or tag_srcrev.find("^{}") == len(tag_srcrev) - 3:
                            msg = "The tag '%s' for recipe '%s' (file '%s') is annotated, but WEBOS_VERSION '%s' is using SHA-1 of last commit included, not of the tag itself '%s'" % (webos_git_repo_tag, pn, file, webos_version, tag_srcrevs[0].split()[0])
                            package_qa_handle_error("webos-enh-sub-error", msg, d)

                if not found_srcrev:
                    if len(tag_srcrevs) < 1:
                        msg = "The revision '%s' defined in WEBOS_VERSION for recipe '%s' (file '%s') doesn't match with tag '%s', tag couldn't be found in refs/tags/" % (webos_srcrev, pn, file, webos_git_repo_tag)
                        package_qa_handle_error("webos-enh-sub-error", msg, d)
                    elif len(tag_srcrevs) == 1:
                        msg = "The revision '%s' defined in WEBOS_VERSION for recipe '%s' (file '%s') doesn't match with tag '%s', which is seen as revision '%s'" % (webos_srcrev, pn, file, webos_git_repo_tag, tag_srcrevs[0].split()[0])
                        package_qa_handle_error("webos-enh-sub-error", msg, d)
                    else:
                        msg = "The revision '%s' defined in WEBOS_VERSION for recipe '%s' (file '%s') doesn't match with tag '%s', which is seen as revisions:\n%s" % (webos_srcrev, pn, file, webos_git_repo_tag, '\n'.join(tag_srcrevs))
                        package_qa_handle_error("webos-enh-sub-error", msg, d)
                # duplicates bitbake's git fetcher functionality added in
                # http://git.openembedded.org/bitbake/commit/?id=89abfbc1953e3711d6c90aff793ee622c22609b1
                # http://git.openembedded.org/bitbake/commit/?id=31467c0afe0346502fcd18bd376f23ea76a27d61
                # http://git.openembedded.org/bitbake/commit/?id=f594cb9f5a18dd0ab2342f96ffc6dba697b35f65
                if not 'nobranch' in urldata[u].parm or urldata[u].parm['nobranch'] != "1":
                    branch_in_src_uri = urldata[u].parm['branch'] if 'branch' in urldata[u].parm else 'master'
                    branch_in_webos_version = d.getVar('WEBOS_GIT_PARAM_BRANCH', True)
                    if branch_in_src_uri != branch_in_webos_version:
                        msg = "Branch is set in WEBOS_VERSION '%s' for recipe '%s' (file '%s') as well as in SRC_URI '%s' and they don't match" % (branch_in_webos_version, pn, file, branch_in_src_uri)
                        package_qa_handle_error("webos-enh-sub-error", msg, d)
                    cmd = "cd %s && git branch -a --contains %s --list origin/%s 2> /dev/null | wc -l" % (checkout, webos_srcrev, branch_in_webos_version)
                    try:
                        output = bb.fetch.runfetchcmd(cmd, d, quiet=True)
                    except bb.fetch2.FetchError:
                        msg = "Unable to check if revision '%s' defined in WEBOS_VERSION for recipe '%s' (file '%s') is included in branch '%s'" % (webos_srcrev, pn, file, branch)
                        package_qa_handle_error("webos-enh-sub-error", msg, d)
                    if len(output.split()) > 1:
                        msg = "Unable to check if revision '%s' defined in WEBOS_VERSION for recipe '%s' (file '%s') is included in branch '%s', unexpected output from '%s': '%s'" % (webos_srcrev, pn, file, branch_in_webos_version, cmd, output)
                        package_qa_handle_error("webos-enh-sub-error", msg, d)
                    if output.split()[0] == "0":
                        msg = "Revision '%s' defined in WEBOS_VERSION for recipe '%s' (file '%s') isn't included in branch '%s'" % (webos_srcrev, pn, file, branch_in_webos_version)
                        package_qa_handle_error("webos-enh-sub-error", msg, d)
}
