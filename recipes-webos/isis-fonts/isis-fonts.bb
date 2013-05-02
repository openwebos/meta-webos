SUMMARY = "Isis-fonts package from isis-project"
SECTION = "webos/fonts"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

# isis-project components don't have submissions
PE = "1"
PV = "0.1"
# NB. Tag is "v${PV}"
SRCREV = "4b9cfe22e7f344db454aa87a53c77ba2e95a1ce5"
PR = "r1"

inherit webos_public_repo
inherit webos_arch_indep

SRC_URI = "${ISIS_PROJECT_GIT_REPO_COMPLETE}"
S = "${WORKDIR}/git"

do_install() {
    install -d ${D}${datadir}/fonts
    install -v -m 644 * ${D}${datadir}/fonts
}

FILES_${PN} = "${datadir}/fonts"
