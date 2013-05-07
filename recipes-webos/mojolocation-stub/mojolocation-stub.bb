# Copyright (c) 2012-2013 LG Electronics, Inc.

SUMMARY = "Location stub service"
SECTION = "webos/services"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

WEBOS_VERSION = "2.0.0-2_717d6e57e7a5bd528cf4bef07027a4e6e7b53f08"
PR = "r4"

#inherit webos_component
inherit webos_public_repo
inherit webos_enhanced_submissions
#inherit webos_cmake
inherit webos_system_bus
inherit webos_arch_indep

SRC_URI = "${OPENWEBOS_GIT_REPO_COMPLETE}"
S = "${WORKDIR}/git"

WEBOS_SYSTEM_BUS_FILES_LOCATION = "${S}/files/sysbus"

do_install() {
    install -d ${D}${webos_servicesdir}

    # NOTE: Name of service directory must match contents (and name) of .service file
    SERVICE_DIR="com.palm.location"
    install -d ${D}${webos_servicesdir}/$SERVICE_DIR/
    cp -vf ${S}/*.js* ${D}${webos_servicesdir}/$SERVICE_DIR/
}

FILES_${PN} += "${webos_servicesdir}"
