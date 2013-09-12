# Copyright (c) 2012-2013 LG Electronics, Inc.

SUMMARY = "Stubbed version of the webOS Remote Diagnostics Library"
SECTION = "webos/libs"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

PROVIDES = "librdx"

WEBOS_VERSION = "2.0.0-9_f226cb05b15bca20903a28ffa2753909d9ad35da"
PR = "r4"

inherit webos_component
inherit webos_public_repo
inherit webos_enhanced_submissions
inherit webos_cmake
inherit webos_library

SRC_URI = "${OPENWEBOS_GIT_REPO_COMPLETE}"
S = "${WORKDIR}/git"

# Must set DEBIAN_NOAUTONAME for all of the packages created by this recipe
DEBIAN_NOAUTONAME_${PN} = "1"
DEBIAN_NOAUTONAME_${PN}-dbg = "1"
DEBIAN_NOAUTONAME_${PN}-dev = "1"
