# Copyright (c) 2012-2013 LG Electronics, Inc.

SUMMARY = "Just Type application for Open webOS"
SECTION = "webos/apps"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

PR = "r7"

inherit webos_public_repo
inherit webos_enhanced_submissions
inherit webos_arch_indep
inherit webos_machine_dep

WEBOS_GIT_PARAM_TAG = "submissions/${WEBOS_SUBMISSION}"
SRC_URI = "${OPENWEBOS_GIT_REPO_COMPLETE}"
S = "${WORKDIR}/git"

do_install() {
    #COPY ENTIRE APP
    install -d ${D}${webos_sysmgr_datadir}/system/luna-applauncher
    cp -vrf ${S}/* ${D}${webos_sysmgr_datadir}/system/luna-applauncher
}

FILES_${PN} += "${webos_sysmgr_datadir}/system"
