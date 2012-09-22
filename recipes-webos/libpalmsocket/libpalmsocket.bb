# (c) Copyright 2012  Hewlett-Packard Development Company, L.P. 


DESCRIPTION = "Palm Socket Library with SSL Support"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"
SECTION = "webos/libs"

DEPENDS = "pmloglib glib-2.0 openssl c-ares pmstatemachineengine"

PR = "r1"

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
        # XXX Temporarily, create a link from the old include path
        install -d ${D}${includedir}/palmsocket/IncsPublic
        for i in ${D}${includedir}/palmsocket/*.h; do ln -snf ../$(basename $i) ${D}${includedir}/palmsocket/IncsPublic; done
}
