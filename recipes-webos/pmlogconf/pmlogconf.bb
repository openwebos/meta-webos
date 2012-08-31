# (c) Copyright 2012  Hewlett-Packard Development Company, L.P. 

DESCRIPTION = "Open webOS logging daemon configuration"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"
SECTION = "webos/base"

inherit webos_component
inherit webos_public_repo
inherit webos_enhanced_submissions
inherit webos_cmake
inherit webos_arch_indep

WEBOS_SVN_MODULE = "submissions;module=${WEBOS_SUBMISSION}"
SRC_URI = "svn://subversion.palm.com/main/nova/palm/pmlogdaemon/${WEBOS_SVN_MODULE};proto=http"
S = "${WORKDIR}/${WEBOS_SUBMISSION}"

