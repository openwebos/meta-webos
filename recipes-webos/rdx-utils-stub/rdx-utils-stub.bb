# Copyright (c) 2012-2013 LG Electronics, Inc.

SUMMARY = "Stubbed implementation of the webOS Remote Diagnostics Utilities"
SECTION = "webos/base"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

RPROVIDES_${PN} = "rdx-utils"

WEBOS_VERSION = "2.0.0-1_381cc3df3ef488694ec6935492dbc162a557b87b"
PR = "r2"

inherit webos_component
inherit webos_public_repo
inherit webos_enhanced_submissions
inherit webos_cmake
inherit webos_arch_indep
inherit webos_program

SRC_URI = "${OPENWEBOS_GIT_REPO_COMPLETE}"
S = "${WORKDIR}/git"
