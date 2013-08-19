# Copyright (c) 2012-2013 LG Electronics, Inc.

SUMMARY = "IMAP sync transport for Open webOS"
SECTION = "webos/base"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS = "jemalloc db8 mojomail-common boost icu libpalmsocket libsandbox pmloglib glib-2.0 cjson luna-service2 c-ares"

PR = "r3"

inherit webos_component
inherit webos_public_repo
inherit webos_enhanced_submissions
inherit webos_cmake
inherit webos_program
inherit webos_system_bus
inherit webos_machine_impl_dep

WEBOS_GIT_PARAM_TAG = "submissions/${WEBOS_SUBMISSION}"
WEBOS_REPO_NAME = "mojomail"
SRC_URI = "${OPENWEBOS_GIT_REPO_COMPLETE}"
S = "${WORKDIR}/git/imap"

FILES_${PN} += "${webos_accttemplatesdir}"
