# Copyright (c) 2012-2013 LG Electronics, Inc.

DESCRIPTION = "Open webOS component to manage all running activities."
LICENSE = "Apache-2.0"
SECTION = "webos/dameons"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS = "luna-service2 db8 boost openssl glib-2.0 pmloglib nyx-lib"

WEBOS_VERSION = "2.0.0-114_487f8bce56b3a361b34380276cd6f73b94d9ccb7"
PR = "r4"

inherit webos_component
inherit webos_public_repo
inherit webos_enhanced_submissions
inherit webos_cmake
inherit webos_system_bus
inherit webos_daemon
inherit webos_machine_impl_dep

SRC_URI = "${OPENWEBOS_GIT_REPO_COMPLETE}"
S = "${WORKDIR}/git"
