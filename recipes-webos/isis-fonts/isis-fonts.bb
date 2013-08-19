SUMMARY = "Isis-fonts package from isis-project"
SECTION = "webos/fonts"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

inherit webos_public_repo
inherit webos_arch_indep
inherit webos_enhanced_submissions

PR = "r0"
# The tag doesn't follow our normal conventions
WEBOS_GIT_PARAM_TAG = "v${WEBOS_SUBMISSION}"
SRC_URI = "${ISIS_PROJECT_GIT_REPO_COMPLETE}"
S = "${WORKDIR}/git"

do_install() {
    install -d ${D}${datadir}/fonts
    install -v -m 644 * ${D}${datadir}/fonts
}

FILES_${PN} = "${datadir}/fonts"
