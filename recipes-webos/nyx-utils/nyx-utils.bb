# Copyright (c) 2012-2013 LG Electronics, Inc.

SUMMARY = "Command line utilities for the Open webOS Platform Portability Layer"
SECTION = "webos/base"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS = "nyx-lib"

WEBOS_VERSION = "1.0.0-11_30a5c469dcdc3f7ceef3bb965501da5abd316028"
PR = "r0"

inherit webos_component
inherit webos_public_repo
inherit webos_enhanced_submissions
inherit webos_cmake
inherit webos_program

SRC_URI = "${OPENWEBOS_GIT_REPO_COMPLETE}"
S = "${WORKDIR}/git"

FILES_${PN} += "${libdir}/nyx/nyxcmd/*"
FILES_${PN}-dbg += "${libdir}/nyx/nyxcmd/.debug/*"
