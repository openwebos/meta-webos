# (c) Copyright 2012  Hewlett-Packard Development Company, L.P. 

# 
# webos_enhanced_submissions
#

# PV is the first underscore-separated field in PREFERRED_VERSION_<packagename>,
# i.e., it includes the submission.
def webos_enhsub_get_pv(pn, d):
        import bb

        preferred_v = bb.data.getVar('PREFERRED_VERSION_' + pn, d, 1) or '0'
        return preferred_v.split('_')[0]

# The component version is PREFERRED_VERSION_<packagename> with the last hyphen-
# separated field removed; i.e., it does not include the submission.
def webos_enhsub_get_component_version(pn, d):
        import bb

        preferred_v = bb.data.getVar('PREFERRED_VERSION_' + pn, d, 1) or '0'
        split_preferred_v = preferred_v.split('-')
        if len(split_preferred_v) == 1:
                # If there's no submission, then the component version can't
                # contain a hyphen
                return preferred_v.split('_')[0]
        return "-".join(split_preferred_v[:-1])

# The submission is the first underscore-separated field in an enhanced
# submission value.
def webos_enhsub_get_submission(pn, d):
        import bb

        enhanced_submission = bb.data.getVar('SUBMISSION_' + pn, d, 1) or '0'
        return enhanced_submission.split('_')[0]


PV = "${@webos_enhsub_get_pv('${PN}', d)}"

# These two are intended for use in the recipes that inherit this file:
WEBOS_COMPONENT_VERSION = "${@webos_enhsub_get_component_version('${PN}', d)}"
WEBOS_SUBMISSION = "${@webos_enhsub_get_submission('${PN}', d)}"

# WARNING: When inheriting from this file, always use WEBOS_SUBMISSION to fetch
# the source; do not use PR.
