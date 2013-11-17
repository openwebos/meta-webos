# Copyright (c) 2012-2013 LG Electronics, Inc.

DESCRIPTION = "Public headers for multiprocess support in LunaSysMgr"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"
SECTION = "webos/devel"

DEPENDS = "luna-sysmgr-ipc luna-webkit-api"

WEBOS_VERSION = "2.0.0-1.02_af410472bb3f0db11fc87ec1962ef4e3c8d82aa3"
PR = "r4"

inherit webos_component
inherit webos_public_repo
inherit webos_enhanced_submissions
inherit webos_cmake
inherit webos_arch_indep

# Depends on TUNE_PKGARCH recipe luna-sysmgr-ipc as detected by bitbake-diffsigs
# Hash for dependent task luna-sysmgr-ipc.bb.do_populate_sysroot changed from a8c364874eceb8a73facd98077dc8967 to 29dbfa1b923c026c2c9fa11fff7a2395
# But because default PACKAGE_ARCH in webOS is MACHINE_ARCH use MACHINE_ARCH here as well.
inherit webos_machine_dep

SRC_URI = "${OPENWEBOS_GIT_REPO_COMPLETE}"
S ="${WORKDIR}/git"

ALLOW_EMPTY_${PN} = "1"
