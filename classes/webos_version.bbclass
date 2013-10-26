# Copyright (c) 2013 LG Electronics, Inc.
#
# webos_version
#
# Functions to parse the fields of a WEBOS_VERSION, which have the following format:
#
#    <component-version>-<submission>[_<40-character-revision-hash>]
#

# PV is the first underscore-separated field in WEBOS_VERSION,
# i.e., it includes the submission. If there is no WEBOS_VERSION
# setting, '0' will be returned.
def webos_version_get_pv(wv):
    return wv.split('_')[0] if wv else '0'

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
# WEBOS_VERSION. Returns None if the field is absent and False if it's invalid.
def webos_version_get_srcrev(wv):
    split_wv = wv.split('_')
    if len(split_wv) == 1:
        return None
    srcrev = split_wv[1]
    if (len(srcrev) != 40 or (False in [c in "abcdef0123456789" for c in srcrev])):
        return False
    return srcrev
