# (c) Copyright 2012  Hewlett-Packard Development Company, L.P. 

DESCRIPTION = "webOS Universal Search Manager"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"
SECTION = "webos/base"

DEPENDS = "libxml2 luna-service2 glib-2.0 cjson sqlite3 "

PR = "r3"

inherit webos_component
inherit webos_public_repo
inherit webos_enhanced_submissions
inherit webos_cmake
inherit webos_system_bus

WEBOS_GIT_TAG = "submissions/${WEBOS_SUBMISSION}"
SRC_URI = "${OPENWEBOS_GIT_REPO}/${PN};tag=${WEBOS_GIT_TAG};protocol=git"
S = "${WORKDIR}/git"

do_install_append() {
        # Create directories and install files into target rootfs
        #  TODO:  Remove with localization
        install -d ${D}/usr/palm/universalsearchmgr/resources/en_us
        install -m 0644 ${S}/desktop-support/UniversalSearchList.json ${D}/usr/palm/universalsearchmgr/resources/en_us
}


FILES_${PN} += "${sysconfdir} ${prefix}/palm"