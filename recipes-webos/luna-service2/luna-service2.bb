# (c) Copyright 2012  Hewlett-Packard Development Company, L.P. 

DESCRIPTION = "Open webOS Luna System Bus library and utilities"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"
SECTION = "webos/base"

DEPENDS = "pmloglib cjson glib-2.0"
RDEPENDS_${PN} = "upstart"

PR = "r7"

# Don't uncomment until CMakeLists.txt installs the role files
#inherit webos_component
inherit webos_public_repo
inherit webos_enhanced_submissions
inherit webos_cmake
inherit webos_library
inherit webos_system_bus
inherit webos_core_os_dep

# This fix-up will be removed shortly. luna-service2 headers must be included
# using '#include <luna-service2/*.h>'
do_install_append() {
        #autotools_stage_all

        # XXX Temporarily, create links from the old locations until all users of
        # luna-service2 convert to using pkg-config
        ln -snf luna-service2/lunaservice.h ${D}${includedir}/lunaservice.h
        ln -snf luna-service2/lunaservice-errors.h ${D}${includedir}/lunaservice-errors.h
        ln -snf lib${PN}.so ${D}${libdir}/liblunaservice.so

        install -d ${D}/var/palm/system-services ${D}/var/mft/palm/system-services
        install -d ${D}/var/palm/ls2/roles/pub ${D}/var/palm/ls2/roles/prv
        install -d ${D}/var/mft/palm/ls2/roles/pub ${D}/var/mft/palm/ls2/roles/prv
        install -d ${D}/var/palm/ls2/services/pub ${D}/var/palm/ls2/services/prv
}

# The following is only needed until ls2 is upgraded to use cmake-modules-webos
EXTRA_OECMAKE += "-DTARGET_CORE_OS:STRING=${WEBOS_TARGET_CORE_OS}"

WEBOS_GIT_TAG = "submissions/${WEBOS_SUBMISSION}"
SRC_URI = "${OPENWEBOS_GIT_REPO}/${PN};tag=${WEBOS_GIT_TAG};protocol=git"
S = "${WORKDIR}/git"
