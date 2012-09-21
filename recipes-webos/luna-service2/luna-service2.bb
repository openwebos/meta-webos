# (c) Copyright 2012  Hewlett-Packard Development Company, L.P. 

DESCRIPTION = "Open webOS Luna System Bus library and utilities"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"
SECTION = "webos/base"

DEPENDS = "pmloglib cjson glib-2.0"
RDEPENDS = "upstart"

PR = "r2"

inherit webos_component
inherit webos_public_repo
inherit webos_enhanced_submissions
inherit webos_cmake
inherit webos_library
inherit webos_system_bus
inherit webos_core_os_dep

# This fix-up will be removed shortly. luna-service2 headers must be included
# using '#include <luna-service/*.h>'
do_install_append() {
        #autotools_stage_all

        # XXX Temporarily, create links from the old locations until all users of
        # luna-service2 convert to using pkg-config
        ln -snf ${PN}/lunaservice.h ${STAGING_INCDIR}
        ln -snf ${PN}/lunaservice-errors.h ${STAGING_INCDIR}
        ln -snf lib${PN}.so ${STAGING_LIBDIR}/liblunaservice.so
        mv ${D}/"${prefix}/${sysconfdir}" ${D}
}

# The following is only needed until ls2 is upgraded to use cmake-modules-webos
EXTRA_OECMAKE += "-DTARGET_CORE_OS:STRING=${WEBOS_TARGET_CORE_OS}"

WEBOS_GIT_TAG = "submissions/${WEBOS_SUBMISSION}"
SRC_URI = "${OPENWEBOS_GIT_REPO}/${PN};tag=${WEBOS_GIT_TAG};protocol=git"
S = "${WORKDIR}/git"
