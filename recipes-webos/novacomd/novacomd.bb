SUMMARY = "NovaCOMd -- Daemon for NovaCOM (device and host)"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"
SECTION = "webos/base"

PR = "r0"

inherit webos_component
inherit webos_public_repo
inherit webos_enhanced_submissions
inherit webos_cmake
inherit webos_daemon
inherit webos_machine_impl_dep

DEPENDS = "${@base_conditional('WEBOS_TARGET_MACHINE_IMPL','host','libusb','nyx-lib',d)}"
RDEPENDS_${PN} = "${@base_conditional('WEBOS_TARGET_MACHINE_IMPL','emulator','iproute2','',d)}"

WEBOS_GIT_PARAM_TAG = "submissions/${WEBOS_SUBMISSION}"
SRC_URI = "${OPENWEBOS_GIT_REPO_COMPLETE}"
S = "${WORKDIR}/git"
