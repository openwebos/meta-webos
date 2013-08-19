# Copyright (c) 2012-2013 LG Electronics, Inc.

SUMMARY = "A module for nodejs that allows dynamic loading and execution of Javascript files"
SECTION = "webos/nodejs/module"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS = "boost node-gyp-native"

PR = "r3"

inherit webos_component
inherit webos_public_repo
inherit webos_enhanced_submissions
inherit webos_library

WEBOS_GIT_PARAM_TAG = "submissions/${WEBOS_SUBMISSION}"
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
    install ${S}/build/Release/webos.node ${D}${libdir}/nodejs/webos.node
    install -d ${D}${webos_prefix}/nodejs
    ln -svnf ${libdir}/nodejs/webos.node ${D}${webos_prefix}/nodejs/
}

# XXX Temporarily add symlink to old location until all users of it are changed
FILES_${PN} += "${webos_prefix}/nodejs"
do_install_append() {
    install -d ${D}${webos_prefix}/nodejs
    ln -svnf ${libdir}/nodejs/webos.node ${D}${webos_prefix}/nodejs/
}

FILES_${PN} += "${libdir}/nodejs"
FILES_${PN}-dbg += "${libdir}/nodejs/.debug"
