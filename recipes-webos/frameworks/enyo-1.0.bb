# (c) Copyright 2012  Hewlett-Packard Development Company, L.P.

SUMMARY = "Enyo 1.0 JavaScript application framework"
SECTION = "webos/frameworks"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

PR = "r4"

#inherit webos_component
inherit webos_public_repo
inherit webos_enhanced_submissions
#inherit webos_cmake
inherit allarch

WEBOS_GIT_TAG = "submissions/${WEBOS_SUBMISSION}"
SRC_URI = "${ENYOJS_GIT_REPO}/${PN};tag=${WEBOS_GIT_TAG};protocol=git"
S = "${WORKDIR}/git"

do_install() {
        # WEBOS_INSTALL_WEBOS_FRAMEWORKSDIR
        install -d ${D}/usr/palm/frameworks/enyo/0.10/framework
        cp -vrf ${S}/framework/* ${D}/usr/palm/frameworks/enyo/0.10/framework

        # Create symlink for enyo/1.0 (points to enyo/0.10)
        ln -vs 0.10 ${D}/usr/palm/frameworks/enyo/1.0
}

FILES_${PN} += "/usr/palm/frameworks"
