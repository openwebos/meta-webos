# (c) Copyright 2012  Hewlett-Packard Development Company, L.P. 

SECTION = "webos/frameworks"
DESCRIPTION = "Loadable foundation frameworks for apps and services"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

INHIBIT_DEFAULT_DEPS = "1"

#inherit webos_component
#inherit webos_public_repo
#inherit webos_enhanced_submissions
#inherit webos_cmake
inherit webos_arch_indep

PR = "r1" 
PV = "${WEBOS_COMPONENT_VERSION}-${WEBOS_SUBMISSION}"
#WEBOS_GIT_TAG = "submissions/${WEBOS_SUBMISSION}"
WEBOS_GIT_TAG = "${WEBOS_SUBMISSION}"
SRC_URI = "${OPENWEBOS_GIT_REPO}/${PN};tag=${WEBOS_GIT_TAG};protocol=git"
S = "${WORKDIR}/git"

do_install() {
        # Create directories and install files into target rootfs

        # WEBOS_INSTALL_WEBOS_FRAMEWORKSDIR
        install -d ${D}/usr/palm/frameworks

        for FRAMEWORK in `ls -d1 ${S}/foundations*` ; do
            FRAMEWORK_DIR=`basename ${FRAMEWORK}`
            install -d ${D}/usr/palm/frameworks/${FRAMEWORK_DIR}/version/1.0/
            cp -rf ${FRAMEWORK}/* ${D}/usr/palm/frameworks/${FRAMEWORK_DIR}/version/1.0/
            # remove test and jasminetest dirs
            rm -rf ${D}/usr/palm/frameworks/${FRAMEWORK_DIR}/version/1.0/*test
        done
}

FILES_${PN} += "/usr/palm/frameworks"

