# Copyright (c) 2012-2013 LG Electronics, Inc.
#
# webos_enhanced_submissions
#
# Parse a WEBOS_VERSION in the following format:
#
#    <component-version>-<enhanced-submission>
#
# where
# <enhanced-submission> is of the form:
#
#    <submission>_<40-character-revision-hash>
#
# setting WEBOS_COMPONENT_VERSION, WEBOS_SUBMISSION, WEBOS_GIT_PARAM_TAG, WEBOS_GIT_REPO_TAG, WEBOS_SRCREV, SRCREV, and PV.
#
# The default tag name is the Open webOS convention for submission tags, i.e., they are of the form:
#    submissions/<integer>
# or, when the component has been branched:
#    submissions/<integer>.<integer>
# where <integer> does not contain leading zeros.
# The default can be overridden by setting WEBOS_GIT_REPO_TAG.

inherit webos_submissions

# SRCREV is the second underscore-separated field in WEBOS_VERSION.
def webos_enhsub_get_srcrev(d, webos_v):
    split_webos_v = webos_v.split('_')
    webos_git_repo_tag = d.getVar('WEBOS_GIT_REPO_TAG', True) or "submissions/%s" % d.getVar('WEBOS_SUBMISSION', True)
    if len(split_webos_v) == 1:
        # If there no hyphen, that means there's no srcrev, return tag name
        return webos_git_repo_tag
    srcrev = split_webos_v[1]
    if (len(srcrev) != 40 or (False in [c in "abcdef0123456789" for c in srcrev])):
        file = d.getVar('FILE', True)
        bb.fatal("%s: WEBOS_VERSION needs to end with _<revision-hash> where revision-hash is the 40-character SHA1 identifier of '%s' tag" % (file, webos_git_repo_tag))
    return srcrev

# Set WEBOS_SRCREV to value from WEBOS_VERSION and then use it as initial value
# for SRCREV and WEBOS_GIT_PARAM_TAG, this way setting SRCREV in recipe after
# inheritting this bbclass or from some config file with override will work and
# checkout right revision without triggering sanity check failure.
WEBOS_SRCREV = "${@webos_enhsub_get_srcrev(d, '${WEBOS_VERSION}')}"
SRCREV = "${WEBOS_SRCREV}"
WEBOS_GIT_PARAM_TAG = "${SRCREV}"

# When WEBOS_SRCREV isn't SHA-1 show error
do_fetch[prefuncs] += "webos_srcrev_sanity_check"
python webos_srcrev_sanity_check() {
    webos_srcrev = d.getVar('WEBOS_SRCREV', True)
    if (len(webos_srcrev) != 40 or (False in [c in "abcdef0123456789" for c in webos_srcrev])):
        file = d.getVar('FILE', True)
        bb.error("%s: WEBOS_VERSION needs contain SRCREV" % file)
}
