# (c) Copyright 2012  Hewlett-Packard Development Company, L.P.

SUMMARY = "Just Type application for Open webOS"
SECTION = "webos/apps"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

PR = "r5"

inherit webos_public_repo
inherit webos_enhanced_submissions
inherit allarch
inherit webos_machine_dep

WEBOS_GIT_TAG = "${WEBOS_SUBMISSION}"
SRC_URI = "${OPENWEBOS_GIT_REPO}/${PN};tag=${WEBOS_GIT_TAG};protocol=git"
S = "${WORKDIR}/git"

do_install() {
        #COPY ENTIRE APP
        install -d ${D}/usr/lib/luna/system/luna-applauncher
        cp -vrf ${S}/* ${D}/usr/lib/luna/system/luna-applauncher
}

FILES_${PN} += "/usr/lib/luna/system"
