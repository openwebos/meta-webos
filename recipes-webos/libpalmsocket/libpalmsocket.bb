# (c) Copyright 2012  Hewlett-Packard Development Company, L.P. 


SECTION = "webos/libs"
DESCRIPTION = "Palm Socket Library with SSL Support"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS = "pmloglib glib-2.0 openssl c-ares pmstatemachineengine"

inherit webos_component
inherit webos_public_repo
inherit webos_enhanced_submissions
inherit webos_cmake
inherit webos_machine_impl_dep
inherit webos_library

WEBOS_GIT_TAG = "submissions/${WEBOS_SUBMISSION}"
SRC_URI = "${OPENWEBOS_GIT_REPO}/${PN};tag=${WEBOS_GIT_TAG};protocol=git"
S = "${WORKDIR}/git"

do_install_append() {
        #autotools_stage_all

        # XXX Temporarily, create a link from the old include path
        install -d ${D}/palmsocket/IncsPublic
        for i in ${D}/palmsocket/*.h; do ln -snf ../$(basename $i) ${D}/palmsocket/IncsPublic/$(basename $i); done
}

FILES_${PN}-dev += "/palmsocket"
