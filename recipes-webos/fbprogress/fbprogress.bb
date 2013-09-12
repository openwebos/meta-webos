# Copyright (c) 2012-2013 LG Electronics, Inc.

SUMMARY = "Utility for drawing progress to the frame buffer"
SECTION = "webos/base"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

RDEPENDS_${PN} = "tar"

WEBOS_VERSION = "2.0.0-22_dab06df8a5f9c5144bf13efd528ee05d6f5fce14"
PR = "r3"

inherit webos_component
inherit webos_public_repo
inherit webos_enhanced_submissions
inherit webos_cmake
inherit webos_program
inherit webos_machine_dep

SRC_URI = "${OPENWEBOS_GIT_REPO_COMPLETE}"
S = "${WORKDIR}/git"
