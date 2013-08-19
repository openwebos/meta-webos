# Copyright (c) 2012-2013 LG Electronics, Inc.

SUMMARY = "Initialization, setup, and font files used by luna-sysmgr and luna-sysservice"
SECTION = "webos/base"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

PR = "r9"

#inherit webos_component TODO
inherit webos_arch_indep
inherit webos_public_repo
inherit webos_enhanced_submissions
inherit webos_cmake

WEBOS_GIT_PARAM_TAG = "submissions/${WEBOS_SUBMISSION}"
SRC_URI = "${OPENWEBOS_GIT_REPO_COMPLETE}"
S = "${WORKDIR}/git"

do_install_append() {
    # Expand fonts tarball
    if [ -e ${S}/files/conf/fonts/fonts.tgz ]; then
        install -d ${D}${datadir}/fonts
        tar xvzf ${S}/files/conf/fonts/fonts.tgz --directory=${D}${datadir}/fonts
        # The CMakeLists.txt unintentionally installs the tarball!!
        rm -f ${D}${datadir}/fonts/fonts.tgz
    fi
    install -d ${D}${webos_sysconfdir}
    install -v -m 644 ${S}/files/conf/locale.txt ${D}${webos_sysconfdir}
    install -v -m 644 ${S}/files/conf/defaultPreferences.txt ${D}${webos_sysconfdir}
}

FILES_${PN} += "${webos_prefix} ${webos_sysconfdir} ${datadir}/fonts"
