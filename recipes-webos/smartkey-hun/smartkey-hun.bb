# (c) Copyright 2012  Hewlett-Packard Development Company, L.P.

DESCRIPTION = "SmartKey is the webOS service for spell checking."
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"
SECTION = "webos/base"

DEPENDS = "libpbnjson cjson glib-2.0 luna-service2 icu hunspell"

PR = "r1"

inherit autotools
inherit webos_component
inherit webos_public_repo
inherit webos_enhanced_submissions

WEBOS_GIT_TAG = "submissions/${WEBOS_SUBMISSION}"
SRC_URI = "${OPENWEBOS_GIT_REPO}/${PN};tag=${WEBOS_GIT_TAG};protocol=git"
S = "${WORKDIR}/git"

EXTRA_OEMAKE = "PLATFORM=${TARGET_ARCH}"

do_install_prepend() {
    export INSTALL_DIR="${D}"
}

do_install_append() {
    chmod o-rwx ${D}${bindir}/com.palm.smartkey
}

