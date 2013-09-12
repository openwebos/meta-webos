# Copyright (c) 2012-2013 LG Electronics, Inc.

SUMMARY = "Enyo 1.0 Browser application"
SECTION = "webos/apps"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

WEBOS_VERSION = "4.0.0-0.21_0f2a339d30023018d1d0d435b2aa0350a32c83e4"
PR = "r4"

inherit webos_public_repo
inherit webos_enhanced_submissions
inherit webos_arch_indep

WEBOS_GIT_REPO_TAG = "${WEBOS_SUBMISSION}"
WEBOS_REPO_NAME = "isis-browser"
SRC_URI = "${ISIS_PROJECT_GIT_REPO_COMPLETE}"
S = "${WORKDIR}/git"

do_install() {
    # WEBOS_INSTALL_WEBOS_COREAPPSDIR
    install -d ${D}${webos_applicationsdir}/${PN}
    #INSTALL DB/KINDS
    install -d ${D}${webos_sysconfdir}/db/kinds
    #INSTALL DB/PERSMISSIONS
    install -d ${D}${webos_sysconfdir}/db/permissions

    cp -vrf ${S}/* ${D}${webos_applicationsdir}/${PN}

    if [ -d db/kinds ]; then
        install -v -m 644 db/kinds/* ${D}${webos_sysconfdir}/db/kinds
    fi
    rm -vrf ${D}${webos_applicationsdir}/${PN}/db/kinds

    if [ -d db/permissions ]; then
        install -v -m 644 db/permissions/* ${D}${webos_sysconfdir}/db/permissions
    fi
    rm -vrf ${D}${webos_applicationsdir}/${PN}/db/permissions
}

FILES_${PN} += "${webos_applicationsdir}"
