# Copyright (c) 2012-2013 LG Electronics, Inc.

SUMMARY = "Open webOS logging library"
SECTION = "webos/libs"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS = "glib-2.0 libpbnjson"

WEBOS_VERSION = "3.0.1-39_07de3b1e719cdc0bf1c340edf04ed783d6540e7d"
PR = "r5"

inherit webos_component
inherit webos_public_repo
inherit webos_enhanced_submissions
inherit webos_cmake
inherit webos_library

SRC_URI = "${OPENWEBOS_GIT_REPO_COMPLETE}"
S = "${WORKDIR}/git"
