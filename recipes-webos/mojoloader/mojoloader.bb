# (c) Copyright 2012  Hewlett-Packard Development Company, L.P. 

SECTION = "webos/frameworks"
DESCRIPTION = "JavaScript loader for foundation frameworks and other loadable libraries"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

INHIBIT_DEFAULT_DEPS = "1"

PR = "r2"

#inherit webos_component
#inherit webos_public_repo
inherit webos_submissions
#inherit webos_cmake
inherit webos_arch_indep

# TODO: Fetch from mojoloader 1.0 once that repo is public
#SRC_URI = "${OPENWEBOS_GIT_REPO}/${PN};tag=${WEBOS_GIT_TAG};protocol=git"

WEBOS_GIT_TAG = "${WEBOS_SUBMISSION}"
# NOTE: Fetch this from build-desktop 7 (since mojoloader 1.0 is still private)
SRC_URI = "${OPENWEBOS_GIT_REPO}/build-desktop;tag=${WEBOS_GIT_TAG};protocol=git"
S = "${WORKDIR}/git"

do_install() {
        # Create directories and install files into target rootfs

        # WEBOS_INSTALL_WEBOS_FRAMEWORKSDIR
        install -d ${D}/usr/palm/frameworks
        #install -m 0644    ${S}/mojoloader.js         ${D}$/usr/palm/frameworks
        install -m 0644    ${S}/mojoloader/mojoloader.js         ${D}/usr/palm/frameworks
}

FILES_${PN} += "/usr/palm/frameworks"
