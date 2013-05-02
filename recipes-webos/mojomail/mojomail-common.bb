# Copyright (c) 2012-2013 LG Electronics, Inc.

SUMMARY = "Common Email Service Library used by other email services"
SECTION = "webos/libs"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS = "jemalloc db8 boost curl libpalmsocket libsandbox pmloglib icu"

WEBOS_VERSION = "2.0.0-99_9a4316e6bcb76445673d48a310b0bd446b280f94"
PR = "r1"

inherit webos_component
inherit webos_public_repo
inherit webos_enhanced_submissions
inherit webos_cmake
inherit webos_library
inherit webos_machine_impl_dep

WEBOS_REPO_NAME = "mojomail"
SRC_URI = "${OPENWEBOS_GIT_REPO_COMPLETE}"
S = "${WORKDIR}/git/common"
