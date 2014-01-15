# Copyright (c) 2012-2014 LG Electronics, Inc.

DESCRIPTION = "CMake modules used by webOS"
LICENSE = "Apache-2.0"
SECTION = "webos/devel/tools"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

WEBOS_VERSION = "1.0.0~rc7-19_5b5c38c7a5c51458f0fbba2f6b733061e0ec9df0"
PR = "r1"

inherit webos_component
inherit webos_public_repo
inherit webos_enhanced_submissions
inherit webos_arch_indep
inherit webos_cmake
inherit native

WEBOS_CMAKE_DEPENDS = ""

SRC_URI = "${OPENWEBOS_GIT_REPO_COMPLETE}"
S = "${WORKDIR}/git"

do_compile() {
     :
}
