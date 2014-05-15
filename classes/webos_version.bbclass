# Copyright (c) 2013-2014 LG Electronics, Inc.
#
# webos_version
#
# Functions to parse the fields of a WEBOS_VERSION, which have the following format:
#
#    <component-version>-<submission>[_<40-character-revision-hash>[;branch=<branch>]]
#

# PV is the first underscore-separated field in WEBOS_VERSION,
# i.e., it includes the submission. If there is no WEBOS_VERSION
# setting, '0' will be returned.
def webos_version_get_pv(wv):
    if not wv:
        return '0'
    split_wv = wv.split(';branch=')
    return split_wv[0].split('_')[0]

# The component version is PV with the last hyphen-separated field
# removed; i.e., it does not include the submission.
def webos_version_get_component_version(wv):
    pv = webos_version_get_pv(wv)
    split_pv = pv.split('-')
    if len(split_pv) == 1:
        # If there's no submission, then the component version can't
        # contain a hyphen
        return split_pv[0]
    return "-".join(split_pv[:-1])

# The submission is the last hyphen-separated field in PV.
# If there is no hyphen in PV setting, '0' will be returned.
def webos_version_get_submission(wv):
    pv = webos_version_get_pv(wv)
    split_pv = pv.split('-')
    if len(split_pv) == 1:
        # If there no hyphen, that means there's no submission
        return '0'
    return split_pv[-1]

# The revision-hash (SRCREV) is the second underscore-separated field in
# WEBOS_VERSION. Returns "INVALID" if the field is absent.
def webos_version_get_srcrev(wv):
    split_wv = wv.split(';branch=')
    split_wv = split_wv[0].split('_')
    if len(split_wv) == 1:
        return "INVALID" # this is default SRCREV value from bitbake.conf
    return split_wv[1]

# The branch is optional parameter, last in WEBOS_VERSION after ;branch=
# when not specified it will use first 2 dot separated parts from submission prefixed with '@'
# in cases where submission has exactly 2 dots in it and "master" in all other cases
def webos_version_get_branch(wv):
    split_wv = wv.split(';branch=')
    if len(split_wv) == 1:
        submission = webos_version_get_submission(wv)
        split_submission = submission.split('.')
        if len(split_submission) == 3:
            # Assume @NN.<branch-name>.MM format (the name of the branch is @NN.<name>)
            return "@%s.%s" % (split_submission[0],split_submission[1])
        return "master"
    return split_wv[1]
