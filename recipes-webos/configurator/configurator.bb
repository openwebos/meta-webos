# Copyright (c) 2012-2013 LG Electronics, Inc.

SUMMARY = "Creates the database schema for Open webOS apps"
SECTION = "webos/base"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS = "luna-service2 db8 cjson glib-2.0 pmloglib"

WEBOS_VERSION = "2.0.0-53_cfabdecb0764fb4739d0e92fe479972bb3259678"
PR = "r3"

inherit webos_component
inherit webos_public_repo
inherit webos_enhanced_submissions
inherit webos_cmake
inherit webos_system_bus
inherit webos_daemon
inherit webos_machine_impl_dep

SRC_URI = "${OPENWEBOS_GIT_REPO_COMPLETE}"
S = "${WORKDIR}/git"
