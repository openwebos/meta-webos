# Copyright (c) 2012-2013 LG Electronics, Inc.

SUMMARY = "Provides image manipulation, preference, timezone and ringtone services for Open webOS components"
SECTION = "webos/base"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS = "luna-service2 libpbnjson qt4-webos uriparser libxml2 sqlite3 pmloglib cjson nyx-lib"

RDEPENDS_${PN} = "sntp"

WEBOS_VERSION = "2.0.1-14_a26a604fb0c52a68eec5357fbf69090100e0fed2"
PR = "r10"

inherit webos_component
inherit webos_public_repo
inherit webos_enhanced_submissions
inherit webos_cmake
inherit webos_system_bus
inherit webos_machine_dep
inherit webos_daemon

SRC_URI = "${OPENWEBOS_GIT_REPO_COMPLETE}"
S = "${WORKDIR}/git"
