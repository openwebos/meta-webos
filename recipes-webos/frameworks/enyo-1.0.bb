# (c) Copyright 2012  Hewlett-Packard Development Company, L.P. 

SECTION = "webos/frameworks"
DESCRIPTION = "JavaScript application framework"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

INHIBIT_DEFAULT_DEPS = "1"

PR = "r3"

#inherit webos_component
#inherit webos_public_repo
inherit webos_enhanced_submissions
#inherit webos_cmake

# TODO: should define in meta-webos/conf/distro/webos.conf:
ENYOJS_GIT_REPO ?= "git://github.com/enyojs"

WEBOS_GIT_TAG = "submissions/${WEBOS_SUBMISSION}"
SRC_URI = "${ENYOJS_GIT_REPO}/${PN};tag=${WEBOS_GIT_TAG};protocol=git"
S = "${WORKDIR}/git"

do_install() {
        # Create directories and install files into target rootfs

        # WEBOS_INSTALL_WEBOS_FRAMEWORKSDIR
        install -d ${D}/usr/palm/frameworks/enyo/0.10/framework
        cp -rf ${S}/framework/* ${D}/usr/palm/frameworks/enyo/0.10/framework

        # Create symlink for enyo/1.0 (points to enyo/0.10)
        OLD_DIR=${PWD}
        cd ${D}/usr/palm/frameworks/enyo/
        ln -s 0.10 1.0
        cd ${OLD_DIR}
}

FILES_${PN} += "/usr/palm/frameworks"

