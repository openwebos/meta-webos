# (c) Copyright 2012  Hewlett-Packard Development Company, L.P.

SECTION = "webos/core-apps"
DESCRIPTION = "webos core apps"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

INHIBIT_DEFAULT_DEPS = "1"

PR = "r2"

#inherit webos_component
#inherit webos_public_repo
inherit webos_enhanced_submissions
#inherit webos_cmake
inherit webos_arch_indep
inherit webos_machine_dep

WEBOS_GIT_TAG = "submissions/${WEBOS_SUBMISSION}"
SRC_URI = "${OPENWEBOS_GIT_REPO}/${PN};tag=${WEBOS_GIT_TAG};protocol=git"
S = "${WORKDIR}/git"

do_install() {
        # Create directories and install files into target rootfs

        # WEBOS_INSTALL_WEBOS_COREAPPSDIR
        install -d ${D}/usr/palm/applications
        #INSTALL DB/KINDS
        install -d ${D}/etc/palm/db/kinds
        #INSTALL DB/PERSMISSIONS
        install -d ${D}/etc/palm/db/permissions

        for COREAPPS in `ls -d1 ${S}/com.palm.app*` ; do
            COREAPPS_DIR=`basename ${COREAPPS}`
            install -d ${D}/usr/palm/applications/${COREAPPS_DIR}/
            cp -rf ${COREAPPS}/* ${D}/usr/palm/applications/${COREAPPS_DIR}/

            if [ -d ${COREAPPS}/configuration/db/kinds ]; then
                install -m 644 ${COREAPPS}/configuration/db/kinds/* ${D}/etc/palm/db/kinds
            fi

            if [ -d ${COREAPPS}/configuration/db/permissions ]; then
                install -m 644 ${COREAPPS}/configuration/db/permissions/* ${D}/etc/palm/db/permissions
            fi
            
       done


}

FILES_${PN} += "/usr/palm/applications"
