# (c) Copyright 2012  Hewlett-Packard Development Company, L.P.

DESCRIPTION = "Mojomail IMAP"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"
SECTION = "webos/base"

DEPENDS = "jemalloc db8 mojomail-common boost icu libpalmsocket libsandbox pmloglib glib-2.0 cjson luna-service2 c-ares"

inherit webos_component
inherit webos_public_repo
inherit webos_enhanced_submissions
inherit webos_cmake
inherit webos_system_bus
inherit webos_machine_impl_dep

PR = "r1"

WEBOS_GIT_TAG = "submissions/${WEBOS_SUBMISSION}"
SRC_URI = "${OPENWEBOS_GIT_REPO}/mojomail;tag=${WEBOS_GIT_TAG};protocol=git"
S = "${WORKDIR}/git/imap"

FILES_${PN} += "${bindir} ${sysconfdir} ${datadir} ${prefix}"
