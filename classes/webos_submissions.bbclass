# Copyright (c) 2012-2013 LG Electronics, Inc.
#
# webos_submissions
#
# Parse a WEBOS_VERSION in the following format:
#
#    <component-version>-<submission>
#
# setting WEBOS_COMPONENT_VERSION, WEBOS_SUBMISSION, and PV.
#

# PV is the first underscore-separated field in WEBOS_VERSION,
# i.e., it includes the submission. If there is no WEBOS_VERSION
# setting, '0' will be returned.
def webos_submissions_get_pv(wv):
    return wv.split('_')[0] if wv else '0'

# The component version is PV with the last hyphen-separated field
# removed; i.e., it does not include the submission.
def webos_submissions_get_component_version(wv):
    pv = webos_submissions_get_pv(wv)
    split_pv = pv.split('-')
    if len(split_pv) == 1:
        # If there's no submission, then the component version can't
        # contain a hyphen
        return split_pv[0]
    return "-".join(split_pv[:-1])

# The submission is the last hyphen-separated field in PV.
# If there is no hyphen in PV setting, '0' will be returned.
def webos_submissions_get_submission(wv):
    pv = webos_submissions_get_pv(wv)
    split_pv = pv.split('-')
    if len(split_pv) == 1:
        # If there no hyphen, that means there's no submission
        return '0'
    return split_pv[-1]

# When WEBOS_VERSION isn't defined show error
do_fetch[prefuncs] += "webos_version_sanity_check"
python webos_version_sanity_check() {
    webos_version = d.getVar('WEBOS_VERSION', True)
    webos_component_version = d.getVar('WEBOS_COMPONENT_VERSION', True)
    pv = d.getVar('PV', True)
    file = d.getVar('FILE', True)
    src_uri = d.getVar('SRC_URI', True)
    if not webos_version or webos_version == '0':
        bb.fatal("%s: WEBOS_VERSION needs to be defined for recipes inheritting webos_submissions" % file)
    if not webos_component_version or webos_component_version == '0':
        bb.fatal("%s: WEBOS_VERSION needs contain PV different than '0'" % file)
    if not pv or pv == '0':
        bb.fatal("%s: WEBOS_VERSION needs contain WEBOS_COMPONENT_VERSION different than '0'" % file)
    if src_uri.find('git://') != -1 and not bb.data.inherits_class('webos_enhanced_submissions', d):
        bb.fatal("%s: inherit webos_enhanced_submissions when the recipe is using git:// in SRC_URI" % file)
}

WEBOS_VERSION ?= "0"
PV = "${@webos_submissions_get_pv('${WEBOS_VERSION}')}"
WEBOS_COMPONENT_VERSION = "${@webos_submissions_get_component_version('${WEBOS_VERSION}')}"
WEBOS_SUBMISSION        = "${@webos_submissions_get_submission('${WEBOS_VERSION}')}"
