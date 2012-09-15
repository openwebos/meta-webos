# (c) Copyright 2012  Hewlett-Packard Development Company, L.P.

DESCRIPTION = "A module for nodejs that allows Javascript access to the Open webOS logging system"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"
SECTION = "webos/nodejs/module"

DEPENDS = "nodejs pmloglib"
# DEPENDS += "xxd-native"

PR = "r0"

inherit webos_component
inherit webos_public_repo
inherit webos_enhanced_submissions
inherit webos_cmake

WEBOS_GIT_TAG = "submissions/${WEBOS_SUBMISSION}"
SRC_URI = "${OPENWEBOS_GIT_REPO}/${PN};tag=${WEBOS_GIT_TAG};protocol=git"
S = "${WORKDIR}/git"

# XXX Should be global -- see [OWEBOS-2424]
webos_prefix = "${prefix}/palm"

# XXX Temporarily add symlink to old location until users of it are changed
FILES_${PN} += "${webos_prefix}/nodejs"
do_install_append() {
	install -d ${D}${webos_prefix}/nodejs
	ln -snf ${libdir}/nodejs/pmloglib.node ${D}${webos_prefix}/nodejs/
}

FILES_${PN} += "${libdir}/nodejs"
FILES_${PN}-dbg += "${libdir}/nodejs/.debug"
