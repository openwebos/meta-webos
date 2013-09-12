# Copyright (c) 2012-2013 LG Electronics, Inc.

SUMMARY = "Open webOS edition of the open-source FreeBSD memory allocation library"
SECTION = "libs"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD;md5=3775480a712fc46a69647678acb234cb"

WEBOS_VERSION = "0.20080828a-0webos9-11_757da381d0331eea928022cc85aa4ea9c20c8ac6"
PR = "r1"

inherit webos_upstream_from_repo
inherit webos_public_repo
inherit webos_enhanced_submissions
inherit webos_cmake
inherit webos_library

SRC_URI = "${OPENWEBOS_GIT_REPO_COMPLETE}"
S = "${WORKDIR}/git"
