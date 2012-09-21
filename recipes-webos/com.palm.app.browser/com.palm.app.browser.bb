# (c) Copyright 2012  Hewlett-Packard Development Company, L.P.

DESCRIPTION = "enyo Browser application"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"
SECTION = "webos/apps"

#require enyo-app-common.inc
PR = "r0"

inherit webos_submissions
inherit webos_arch_indep

WEBOS_GIT_TAG = "${WEBOS_SUBMISSION}"
SRC_URI = "${ISIS-PROJECT_GIT_REPO}/isis-browser;tag=${WEBOS_GIT_TAG};protocol=git"
S = "${WORKDIR}/git"

do_install() {
    # WEBOS_INSTALL_WEBOS_COREAPPSDIR
    install -d ${D}${prefix}/palm/applications/${PN}
    #INSTALL DB/KINDS
    install -d ${D}${sysconfdir}/palm/db/kinds
    #INSTALL DB/PERSMISSIONS
    install -d ${D}${sysconfdir}/palm/db/permissions

    cp -rf ${S}/* ${D}${prefix}/palm/applications/${PN}

    if [ -d db/kinds ]; then
        install -m 644 db/kinds/* ${D}${sysconfdir}/palm/db/kinds
    fi
    rm -rf ${D}${prefix}/palm/applications/${PN}/db/kinds

    if [ -d db/permissions ]; then
        install -m 644 db/permissions/* ${D}${sysconfdir}/palm/db/permissions
    fi
    rm -rf ${D}${prefix}/palm/applications/${PN}/db/permissions
}

FILES_${PN} += "${prefix}/palm/applications"
