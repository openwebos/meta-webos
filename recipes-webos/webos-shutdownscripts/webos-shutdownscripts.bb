# Copyright (c) 2012-2013 LG Electronics, Inc.

SUMMARY = "Open webOS shutdown scripts"
SECTION = "webos/base"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

RDEPENDS_${PN} = "fbprogress"

WEBOS_VERSION = "2.1.0-36_9d4d6a73e7f4bc33fa5cee21274400a23485784f"
PR = "r3"

inherit webos_component
inherit webos_public_repo
inherit webos_enhanced_submissions
inherit webos_cmake
inherit webos_arch_indep

# Depends on MACHINE_ARCH recipe fbprogress as detected by bitbake-diffsigs
# Hash for dependent task fbprogress.bb.do_packagedata changed from 0f207eedf6f0279c8f8351d7e2067b9d to 85c28e7ff4dd0c99f301c4f61052ff89
inherit webos_machine_dep

SRC_URI = "${OPENWEBOS_GIT_REPO_COMPLETE}"
S = "${WORKDIR}/git"
