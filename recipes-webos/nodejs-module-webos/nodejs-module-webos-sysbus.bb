# Copyright (c) 2012-2013 LG Electronics, Inc.

SUMMARY = "A module for nodejs that allows Javascript access to the Open webOS system bus"
SECTION = "webos/nodejs/module"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS = "luna-service2 node-gyp-native"

WEBOS_VERSION = "3.0.1-29_706d63157737c281c52038eb644528adf8fc059a"
PR = "r4"

inherit webos_component
inherit webos_public_repo
inherit webos_enhanced_submissions
inherit webos_library
inherit webos_system_bus

SRC_URI = "${OPENWEBOS_GIT_REPO_COMPLETE}"
S = "${WORKDIR}/git"

do_configure() {
    export LD="${CXX}"
    export GYP_DEFINES="sysroot=${STAGING_DIR_HOST}"
    node-gyp --arch ${TARGET_ARCH} configure
}

do_compile() {
    export LD="${CXX}"
    export GYP_DEFINES="sysroot=${STAGING_DIR_HOST}"
    node-gyp --arch ${TARGET_ARCH} build
}

do_install() {
    install -d ${D}${libdir}/nodejs
    install ${S}/build/Release/webos-sysbus.node ${D}${libdir}/nodejs/webos-sysbus.node
    install ${S}/src/palmbus.js ${D}${libdir}/nodejs/palmbus.js
}

# XXX Temporarily add symlink to old location until all users of it are changed
FILES_${PN} += "${webos_prefix}/nodejs"
do_install_append() {
    install -d ${D}${webos_prefix}/nodejs
    ln -svnf ${libdir}/nodejs/palmbus.js ${D}${webos_prefix}/nodejs/
    # The CMake build did this with macros
    install -d ${D}${webos_sysbus_prvrolesdir}
    sed "s|@WEBOS_INSTALL_BINDIR@|$bindir|" < ${S}/files/sysbus/com.palm.nodejs.json.prv.in > ${D}${webos_sysbus_prvrolesdir}/com.palm.nodejs.json
    install -d ${D}${webos_sysbus_pubrolesdir}
    sed "s|@WEBOS_INSTALL_BINDIR@|$bindir|" < ${S}/files/sysbus/com.palm.nodejs.json.pub.in > ${D}${webos_sysbus_pubrolesdir}/com.palm.nodejs.json
}

FILES_${PN} += "${libdir}/nodejs"
FILES_${PN}-dbg += "${libdir}/nodejs/.debug"
