#      Copyright (c) 2012 Hewlett-Packard Development Company, L.P.

SECTION = "Linux/System"
DESCRIPTION = "MojoDB Service"
LICENSE = "Apache 2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS = "db luna-service2 luna-prefs jemalloc icu pmloglib curl"

inherit webos_component
inherit webos_public_repo
inherit webos_submissions
inherit webos_cmake

PR = "r1"
PV = "${WEBOS_COMPONENT_VERSION}-${WEBOS_SUBMISSION}"

WEBOS_GIT_TAG = "submissions/${WEBOS_SUBMISSION}"
SRC_URI = "${OPENWEBOS_GIT_REPO}/${PN};tag=${WEBOS_GIT_TAG};protocol=git"
S="${WORKDIR}/git"

FILES_${PN} += "${bindir} ${libdir} ${sysconfdir}"


