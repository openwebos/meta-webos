# (c) Copyright 2012  Hewlett-Packard Development Company, L.P.

SUMMARY = "Enyo 1.0 Browser application"
SECTION = "webos/apps"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

PR = "r2"

inherit webos_public_repo
inherit webos_submissions
inherit allarch

WEBOS_GIT_TAG = "${WEBOS_SUBMISSION}"
SRC_URI = "${ISIS_PROJECT_GIT_REPO}/isis-browser;tag=${WEBOS_GIT_TAG};protocol=git"
S = "${WORKDIR}/git"

do_install() {
    # WEBOS_INSTALL_WEBOS_COREAPPSDIR
    install -d ${D}${prefix}/palm/applications/${PN}
    #INSTALL DB/KINDS
    install -d ${D}${sysconfdir}/palm/db/kinds
    #INSTALL DB/PERSMISSIONS
    install -d ${D}${sysconfdir}/palm/db/permissions

    cp -vrf ${S}/* ${D}${prefix}/palm/applications/${PN}

    if [ -d db/kinds ]; then
        install -v -m 644 db/kinds/* ${D}${sysconfdir}/palm/db/kinds
    fi
    rm -vrf ${D}${prefix}/palm/applications/${PN}/db/kinds

    if [ -d db/permissions ]; then
        install -v -m 644 db/permissions/* ${D}${sysconfdir}/palm/db/permissions
    fi
    rm -vrf ${D}${prefix}/palm/applications/${PN}/db/permissions
}

FILES_${PN} += "${prefix}/palm/applications"
