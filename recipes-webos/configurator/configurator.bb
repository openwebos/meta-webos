# (c) Copyright 2012  Hewlett-Packard Development Company, L.P.

DESCRIPTION = "Configurator for DB kinds, watches, and file cache types."
LICENSE = "Apache-2.0"
SECTION = "webos/base"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS = "luna-service2 db8 cjson glib-2.0"

inherit webos_component
inherit webos_public_repo
inherit webos_enhanced_submissions
inherit webos_cmake
inherit webos_system_bus
inherit webos_daemon
inherit webos_machine_impl_dep

PR = "r1"

WEBOS_GIT_TAG = "submissions/${WEBOS_SUBMISSION}"
SRC_URI = "${OPENWEBOS_GIT_REPO}/${PN};tag=${WEBOS_GIT_TAG};protocol=git"
S = "${WORKDIR}/git"

FILES_${PN} += "${bindir} ${sysconfdir} ${datadir}"
