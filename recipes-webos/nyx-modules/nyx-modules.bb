# (c) Copyright 2012  Hewlett-Packard Development Company, L.P. 

DESCRIPTION = "webOS portability layer - device-specific modules"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"
SECTION = "webos/base"

DEPENDS = "nyx-lib glib-2.0 luna-service2"

PR = "r2"

inherit webos_component
inherit webos_public_repo
inherit webos_enhanced_submissions
inherit webos_cmake
inherit webos_machine_dep
inherit webos_machine_impl_dep
inherit webos_core_os_dep

WEBOS_GIT_TAG = "submissions/${WEBOS_SUBMISSION}"
SRC_URI = "${OPENWEBOS_GIT_REPO}/${PN};tag=${WEBOS_GIT_TAG};protocol=git"
S = "${WORKDIR}/git"

FILES_${PN} += "${libdir}/nyx/modules/*"
FILES_${PN}-dbg += "${libdir}/nyx/modules/.debug/*"
