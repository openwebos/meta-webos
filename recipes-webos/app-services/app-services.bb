# (c) Copyright 2012  Hewlett-Packard Development Company, L.P.

DESCRIPTION = "JS Services for Apps"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"
SECTION = "webos/services"

INHIBIT_DEFAULT_DEPS = "1"

#inherit webos_component
#inherit webos_public_repo
#inherit webos_enhanced_submissions
#inherit webos_cmake
inherit webos_arch_indep
inherit webos_machine_dep

PR = "r1"
PV = "${WEBOS_COMPONENT_VERSION}-${WEBOS_SUBMISSION}"
WEBOS_GIT_TAG = "submissions/${WEBOS_SUBMISSION}"
SRC_URI = "${OPENWEBOS_GIT_REPO}/${PN};tag=${WEBOS_GIT_TAG};protocol=git"
S = "${WORKDIR}/git"

do_install() {
        # Create directories and install files into target rootfs

        # WEBOS_INSTALL_WEBOS_SERVICESDIR
        install -d ${D}/usr/palm/services
        install -d ${D}/etc/palm/db/kinds
        install -d ${D}/etc/palm/db/permissions
        install -d ${D}/etc/palm/activities
        install -d ${D}/usr/share/ls2/services
        install -d ${D}/usr/share/ls2/system-services
        install -d ${D}/usr/share/ls2/roles/prv

        for SERVICE in `ls -d1 ${S}/com.palm.service.*` ; do
            SERVICE_DIR=`basename ${SERVICE}`
            install -d ${D}/usr/palm/services/${SERVICE_DIR}/
            cp -rf ${SERVICE}/* ${D}/usr/palm/services/${SERVICE_DIR}/
            # Copy db8 kinds, permissions and activities
            cp -rf ${SERVICE}/db/kinds/* ${D}/etc/palm/db/kinds/ 2> /dev/null || true
            cp -rf ${SERVICE}/db/permissions/* ${D}/etc/palm/db/permissions/ 2> /dev/null || true
           cp -rf ${SERVICE}/activities/* ${D}/etc/palm/activities/ 2> /dev/null || true
    # Copy services and roles files
           cp -rf ${SERVICE}/files/sysbus/*.json ${D}/usr/share/ls2/roles/prv 2> /dev/null || true
           cp -rf ${SERVICE}/desktop-support/*.service ${D}/usr/share/ls2/system-services 2> /dev/null || true
        done

# install account services files in public service directory.
        cp -rf ${S}/com.palm.service.accounts/desktop-support/*.service ${D}/usr/share/ls2/services 2> /dev/null || true

# install account service desktop credentials db kind 
       cp -rf ${S}/com.palm.service.accounts/desktop/com.palm.account.credentails ${D}/etc/palm/db/kinds 2> /dev/null || true

# install account templates.
       install -d ${D}/usr/palm/public/accounts 2> /dev/null || true
       cp -rf ${S}/account-templates/palmprofile/com.palm.palmprofile ${D}/usr/palm/public/accounts/

# install temp db kinds and permissions
        install -d ${D}/etc/palm/tempdb/kinds 2> /dev/null || true
        install -d ${D}/etc/palm/tempdb/permissions 2> /dev/null || true
        cp -rf com.palm.service.accounts/tempdb/kinds/* ${D}/etc/palm/tempdb/kinds/ 2> /dev/null || true
        cp -rf com.palm.service.accounts/tempdb/permissions/* ${D}/etc/palm/tempdb/permissions/ 2> /dev/null || true
}

FILES_${PN} += "/usr/palm/services /etc/palm/ /usr/share/ls2 /usr/palm/public"


