# (c) Copyright 2012  Hewlett-Packard Development Company, L.P. 
#
# webos_submissions
#
# Supply the "enhanced submissions" interface so that either can be used by
# recipes.
#

# These two are intended for use in the recipes that inherit this file:
WEBOS_COMPONENT_VERSION = "${PV}"

WEBOS_SUBMISSION = "${@bb.data.getVar('SUBMISSION_${PN}', d, 1) or '0'}"

PR = "${WEBOS_SUBMISSION}"

