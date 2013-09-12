# Copyright (c) 2012-2013 LG Electronics, Inc.

SUMMARY = "Library for dynamically generating webOS system bus role files for webOS JavaScript services"
SECTION = "webos/libs"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

WEBOS_VERSION = "2.1.0-19_4b7684cbf137bd34cd7f6ed2ab23558930ab2dbd"
PR = "r3"

inherit webos_component
inherit webos_public_repo
inherit webos_enhanced_submissions 
inherit webos_cmake
inherit webos_library

SRC_URI = "${OPENWEBOS_GIT_REPO_COMPLETE}"
S = "${WORKDIR}/git"

FILES_${PN} += "${datadir}/rolegen"
