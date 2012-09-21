# (c) Copyright 2012  Hewlett-Packard Development Company, L.P.


DESCRIPTION = "Mojomail Common library."
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"
SECTION = "webos/libs"

DEPENDS = "jemalloc db8 boost curl libpalmsocket libsandbox pmloglib icu"

inherit webos_component
inherit webos_public_repo
inherit webos_enhanced_submissions
inherit webos_cmake
inherit webos_machine_impl_dep
inherit webos_library

PR = "r0"

WEBOS_GIT_TAG = "submissions/${WEBOS_SUBMISSION}"
SRC_URI = "${OPENWEBOS_GIT_REPO}/mojomail;tag=${WEBOS_GIT_TAG};protocol=git"
S = "${WORKDIR}/git/common"

FILES_${PN} = "${libdir} ${sysconfdir} ${webos_pkgconfigdir} ${webos_sysconfdir}"
