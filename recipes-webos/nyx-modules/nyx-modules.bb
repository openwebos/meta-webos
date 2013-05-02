# Copyright (c) 2012-2013 LG Electronics, Inc.

SUMMARY = "webOS portability layer - device-specific modules"
SECTION = "webos/base"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS = "nyx-lib glib-2.0 luna-service2 openssl"

VBOX_RDEPENDS = ""
VBOX_RDEPENDS_qemux86 = "vboxguestdrivers"
RDEPENDS_${PN} = "lsb ${VBOX_RDEPENDS}"

WEBOS_VERSION = "5.2.0-90_033656269c325add774e920698f23e54bd36520c"
PR = "r9"

EXTRA_OECMAKE += "-DDISTRO_VERSION:STRING='${DISTRO_VERSION}' -DDISTRO_NAME:STRING='${DISTRO_NAME}' \
                  -DWEBOS_DISTRO_API_VERSION:STRING='${WEBOS_DISTRO_API_VERSION}' \
                  -DWEBOS_DISTRO_RELEASE_CODENAME:STRING='${WEBOS_DISTRO_RELEASE_CODENAME}' \
                  -DWEBOS_DISTRO_BUILD_ID:STRING='${WEBOS_DISTRO_BUILD_ID}'"

# Only pass in a value for the Manufacturing version if one is actually
# defined. Otherwise, let the CMake script provide the default value.
#
# Defining it to be the empty string will override the default used in
# the CMake script.
WEBOS_DISTRO_MANUFACTURING_VERSION ??= ""
EXTRA_OECMAKE += "${@ '-DWEBOS_DISTRO_MANUFACTURING_VERSION:STRING="${WEBOS_DISTRO_MANUFACTURING_VERSION}"' \
                  if d.getVar('WEBOS_DISTRO_MANUFACTURING_VERSION',True) != '' else ''}"

inherit webos_component
inherit webos_public_repo
inherit webos_enhanced_submissions
inherit webos_cmake
inherit webos_library
inherit webos_machine_dep
inherit webos_machine_impl_dep
inherit webos_core_os_dep

SRC_URI = "${OPENWEBOS_GIT_REPO_COMPLETE}"
S = "${WORKDIR}/git"

FILES_${PN} += "${libdir}/nyx/modules/*"
FILES_${PN}-dbg += "${libdir}/nyx/modules/.debug/*"
