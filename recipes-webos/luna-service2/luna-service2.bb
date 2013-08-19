# Copyright (c) 2012-2013 LG Electronics, Inc.

SUMMARY = "Open webOS Luna System Bus library, daemon, and utilities"
SECTION = "webos/base"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS = "pmloglib cjson glib-2.0"

PR = "r9"

inherit webos_component
inherit webos_public_repo
inherit webos_enhanced_submissions
inherit webos_cmake
inherit webos_library
inherit webos_daemon
inherit webos_program
inherit webos_system_bus
inherit webos_core_os_dep
inherit webos_machine_impl_dep

WEBOS_GIT_PARAM_TAG = "submissions/${WEBOS_SUBMISSION}"
SRC_URI = "${OPENWEBOS_GIT_REPO_COMPLETE}"
S = "${WORKDIR}/git"

# This fix-up will be removed shortly. luna-service2 headers must be included
# using '#include <luna-service2/*.h>'
do_install_append() {
    # XXX Temporarily, create links from the old locations until all users of
    # luna-service2 convert to using pkg-config
    ln -svnf luna-service2/lunaservice.h ${D}${includedir}/lunaservice.h
    ln -svnf luna-service2/lunaservice-errors.h ${D}${includedir}/lunaservice-errors.h
    ln -svnf lib${PN}.so ${D}${libdir}/liblunaservice.so
}
