# (c) Copyright 2012  Hewlett-Packard Development Company, L.P.

SUMMARY = "A module for nodejs that allows dynamic loading and execution of Javascript files"
SECTION = "webos/nodejs/module"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS = "nodejs boost"

PR = "r1"

inherit webos_component
inherit webos_public_repo
inherit webos_enhanced_submissions
inherit webos_cmake
inherit webos_library

WEBOS_GIT_TAG = "submissions/${WEBOS_SUBMISSION}"
SRC_URI = "${OPENWEBOS_GIT_REPO}/${PN};tag=${WEBOS_GIT_TAG};protocol=git"
S = "${WORKDIR}/git"

# XXX Should be global -- see [OWEBOS-2424]
webos_prefix = "${prefix}/palm"

# XXX Temporarily add symlink to old location until users of it are changed
FILES_${PN} += "${webos_prefix}/nodejs"
do_install_append() {
	install -d ${D}${webos_prefix}/nodejs
	ln -svnf ${libdir}/nodejs/webos.node ${D}${webos_prefix}/nodejs/
}

FILES_${PN} += "${libdir}/nodejs"
FILES_${PN}-dbg += "${libdir}/nodejs/.debug"
