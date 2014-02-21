# Copyright (c) 2012-2013 LG Electronics, Inc.

SUMMARY = "Enyo 1.0 JavaScript application framework"
SECTION = "webos/frameworks"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

WEBOS_VERSION = "1.0-128.2_e68b0e2b0523179c6733982d91fafaf36e85e1bd"
PR = "r5"

#inherit webos_component
inherit webos_public_repo
inherit webos_enhanced_submissions
#inherit webos_cmake
inherit webos_arch_indep

SRC_URI = "${ENYOJS_GIT_REPO}/${PN}${WEBOS_GIT_TAG}"
S = "${WORKDIR}/git"

do_install() {
    install -d ${D}${webos_frameworksdir}/enyo/0.10/framework
    cp -vrf ${S}/framework/* ${D}${webos_frameworksdir}/enyo/0.10/framework

    # Create symlink for enyo/1.0 (points to enyo/0.10)
    ln -vs 0.10 ${D}${webos_frameworksdir}/enyo/1.0
}

FILES_${PN} += "${webos_frameworksdir}"
