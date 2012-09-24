# (c) Copyright 2012  Hewlett-Packard Development Company, L.P. 


DESCRIPTION = "Sleep scheduling policy daemon"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"
SECTION = "webos/dameons"

DEPENDS = "nyx-lib luna-service2 cjson libxml2 sqlite3 glib-2.0 powerd"

PR = "r1"

inherit webos_component
inherit webos_public_repo
inherit webos_enhanced_submissions
inherit webos_cmake
inherit webos_daemon
inherit webos_system_bus

WEBOS_GIT_TAG = "submissions/${WEBOS_SUBMISSION}"
SRC_URI = "${OPENWEBOS_GIT_REPO}/${PN};tag=${WEBOS_GIT_TAG};protocol=git"
S = "${WORKDIR}/git"

SRC_URI += " file://fix_compile_error.patch "

# EXTRA_OECMAKE += " -DTARGET_CORE_OS:STRING=${OPENWEBOS_TARGET}"


