# Copyright (c) 2012-2013 LG Electronics, Inc.

DESCRIPTION = "Public headers for multiprocess support in LunaSysMgr"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"
SECTION = "webos/devel"

DEPENDS = "luna-sysmgr-ipc luna-webkit-api"

WEBOS_VERSION = "2.0.0-1.02_6d4f9949f3d5ce31a260f61ee407e43376a4af65"
PR = "r3"

inherit webos_component
inherit webos_public_repo
inherit webos_enhanced_submissions
inherit webos_cmake
inherit webos_arch_indep

SRC_URI = "${OPENWEBOS_GIT_REPO_COMPLETE}"
S ="${WORKDIR}/git"

ALLOW_EMPTY_${PN} = "1"

