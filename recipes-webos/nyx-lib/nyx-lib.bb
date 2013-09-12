# Copyright (c) 2012-2013 LG Electronics, Inc.

SUMMARY = "Open webOS portability layer - library"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"
SECTION = "webos/libs"

# nyx-lib needs nyx-modules at runtime, but a runtime dependency is not defined
# here because nyx-modules is MACHINE_ARCH (e.g. qemux86), while nyx-lib is
# TUNE_PKGARCH  (e.g. i586). Instead, it is pulled into the image by adding it to
# the RDPENDS_${PN} of packagegroup-webos-extended. Putting
#   RDEPENDS_${PN} = "nyx-modules"
# here would cause bitbake to re-execute the do_package task for each MACHINE,
# even if these MACHINE-s were all i586 and should therefore share the same nyx-lib
# .ipk and sstate files. (The reason do_package is re-executed when a component
# in any of the R* variables is re-built is because its package name is stored in
# this component's .ipk and it may have changed because debian.bbclass is inherited.)

DEPENDS = "glib-2.0"

WEBOS_VERSION = "5.2.0-67_840f318a07ebf7b0deccf5effcacd66eb48c4db5"
PR = "r6"

inherit webos_component
inherit webos_public_repo
inherit webos_enhanced_submissions
inherit webos_cmake
inherit webos_library

SRC_URI = "${OPENWEBOS_GIT_REPO_COMPLETE}"
S = "${WORKDIR}/git"
