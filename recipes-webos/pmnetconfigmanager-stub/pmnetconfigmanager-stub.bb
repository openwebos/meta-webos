# (c) Copyright 2012  Hewlett-Packard Development Company, L.P.

DESCRIPTION = "ConnectionManager stub service"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"
SECTION = "webos/services"

PR = "r1"

#inherit webos_component
inherit webos_public_repo
inherit webos_enhanced_submissions
#inherit webos_cmake
inherit webos_arch_indep

WEBOS_GIT_TAG = "submissions/${WEBOS_SUBMISSION}"
SRC_URI = "${OPENWEBOS_GIT_REPO}/${PN};tag=${WEBOS_GIT_TAG};protocol=git"
S = "${WORKDIR}/git"

do_install() {
        # Create directories and install files into target rootfs

        # WEBOS_INSTALL_WEBOS_SERVICESDIR
        install -d ${D}/usr/palm/services
        install -d ${D}/usr/share/dbus-1/services
        install -d ${D}/usr/share/dbus-1/system-services
        install -d ${D}/usr/share/ls2/roles/prv
        install -d ${D}/usr/share/ls2/roles/pub

        # NOTE: Name of service directory must match contents (and name) of .service file
        SERVICE_DIR="com.palm.connectionmanager"
        install -d ${D}/usr/palm/services/$SERVICE_DIR/
        cp -f ${S}/*.js* ${D}/usr/palm/services/$SERVICE_DIR/

        # Copy (public and private) service and role files
        cp -f ${S}/files/sysbus/*.json ${D}/usr/share/ls2/roles/prv
        cp -f ${S}/files/sysbus/*.json ${D}/usr/share/ls2/roles/pub
        cp -f ${S}/files/sysbus/*.service ${D}/usr/share/dbus-1/services
        cp -f ${S}/files/sysbus/*.service ${D}/usr/share/dbus-1/system-services
}

FILES_${PN} += "/usr/palm/services /usr/share/ /usr/palm/public"
