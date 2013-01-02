# (c) Copyright 2012  Hewlett-Packard Development Company, L.P.

SUMMARY = "Location stub service"
SECTION = "webos/services"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

PR = "r3"

#inherit webos_component
inherit webos_public_repo
inherit webos_enhanced_submissions
#inherit webos_cmake
# XXX Uncomment should webos_system_bus ever look in ${S}/files/sysbus as well as ${S}/service
#inherit webos_system_bus
inherit webos_arch_indep

WEBOS_GIT_TAG = "submissions/${WEBOS_SUBMISSION}"
SRC_URI = "${OPENWEBOS_GIT_REPO}/${PN};tag=${WEBOS_GIT_TAG};protocol=git"
S = "${WORKDIR}/git"

do_install() {
    install -d ${D}${webos_servicesdir}
    install -d ${D}${webos_sysbus_pubservicesdir}
    install -d ${D}${webos_sysbus_prvservicesdir}
    install -d ${D}${webos_sysbus_pubrolesdir}
    install -d ${D}${webos_sysbus_prvrolesdir}

    # NOTE: Name of service directory must match contents (and name) of .service file
    SERVICE_DIR="com.palm.location"
    install -d ${D}${webos_servicesdir}/$SERVICE_DIR/
    cp -vf ${S}/*.js* ${D}${webos_servicesdir}/$SERVICE_DIR/

    # Copy (public and private) service and role files
    cp -vf ${S}/files/sysbus/*.service ${D}${webos_sysbus_pubservicesdir}
    cp -vf ${S}/files/sysbus/*.service ${D}${webos_sysbus_prvservicesdir}
    cp -vf ${S}/files/sysbus/*.json    ${D}${webos_sysbus_pubrolesdir}
    cp -vf ${S}/files/sysbus/*.json    ${D}${webos_sysbus_prvrolesdir}
}

FILES_${PN} += "${webos_servicesdir}"
FILES_${PN} += "${webos_sysbus_pubservicesdir} ${webos_sysbus_prvservicesdir}"
FILES_${PN} += "${webos_sysbus_pubrolesdir}    ${webos_sysbus_prvrolesdir}"
