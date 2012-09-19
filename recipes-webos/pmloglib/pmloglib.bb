# (c) Copyright 2012  Hewlett-Packard Development Company, L.P. 

DESCRIPTION = "Open webOS logging library"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"
SECTION = "webos/libs"

# TODO : temporary disable this runtime dependency
# RDEPENDS = "logd-conf"

PR = "r0"

inherit webos_component
inherit webos_public_repo
inherit webos_enhanced_submissions
inherit webos_cmake
inherit webos_library

do_install_append() {
        #autotools_stage_all

        # XXX Temporarily, create a link from the old include path
        install -d ${STAGING_INCDIR}/PmLogLib/IncsPublic
        ln -snf ../PmLogLib.h ${STAGING_INCDIR}/PmLogLib/IncsPublic/PmLogLib.h
}

# Can override in branch.conf to fetch from HEAD.
WEBOS_GIT_TAG = "submissions/${WEBOS_SUBMISSION}"
SRC_URI = "${OPENWEBOS_GIT_REPO}/${PN};tag=${WEBOS_GIT_TAG};protocol=git"
S = "${WORKDIR}/git"
