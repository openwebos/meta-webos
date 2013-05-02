# Copyright (c) 2012-2013 LG Electronics, Inc.

SUMMARY = "Open webOS library for implementing finite state machines"
SECTION = "webos/libs"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS = "pmloglib"

WEBOS_VERSION = "2.0.0-13_88e0b6129e6e7ee939e3622fd84537694df04bde"
PR = "r4"

inherit webos_component
inherit webos_public_repo
inherit webos_enhanced_submissions
inherit webos_cmake
inherit webos_library

SRC_URI = "${OPENWEBOS_GIT_REPO_COMPLETE}"
S = "${WORKDIR}/git"
