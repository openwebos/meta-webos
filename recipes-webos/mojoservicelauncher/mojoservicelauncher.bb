# (c) Copyright 2012  Hewlett-Packard Development Company, L.P. 

DESCRIPTION = "Service launcher for Open webOS services"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"
SECTION = "webos/frameworks"

DEPENDS = "boost libpbnjson"
# fork_server.js wants to load these:
RDEPENDS = "nodejs-module-webos-dynaload nodejs-module-webos-pmlog nodejs-module-webos-sysbus mojoloader"

PR = "r0"

inherit webos_component
inherit webos_public_repo
inherit webos_enhanced_submissions
inherit webos_cmake
inherit webos_daemon

WEBOS_GIT_TAG = "submissions/${WEBOS_SUBMISSION}"
SRC_URI = "${OPENWEBOS_GIT_REPO}/${PN};tag=${WEBOS_GIT_TAG};protocol=git"
S = "${WORKDIR}/git"

# XXX Should be global -- see [OWEBOS-2424]
webos_prefix = "${prefix}/palm"
webos_servicesdir = "${prefix}/palm/services"
webos_frameworksdir = "${prefix}/palm/frameworks"

FILES_${PN} += "${webos_prefix}/nodejs ${webos_servicesdir} ${webos_frameworksdir}"
FILES_${PN}-dbg += "${webos_prefix}/nodejs/.debug/*"

