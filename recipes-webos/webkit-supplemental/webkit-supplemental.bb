# (c) Copyright 2012  Hewlett-Packard Development Company, L.P.

SUMMARY = "WebKit supplemental features for Open webOS"
SECTION = "webos/libs"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS = "qt4-webos webkit-webos qmake-webos-native"

PR = "r3"

inherit webos_public_repo
inherit webos_qmake
inherit webos_submissions
inherit webos_library

WEBOS_GIT_TAG = "${WEBOS_SUBMISSION}"
SRC_URI = "${ISIS_PROJECT_GIT_REPO}/WebKitSupplemental;tag=${WEBOS_GIT_TAG};protocol=git"
S = "${WORKDIR}/git"

PALM_CC_OPT = "-O2"
WEBOS_BUILD_DIR = "build-${MACHINE}"
export STAGING_DIR
export TARGET_ARCH

EXTRA_OEMAKE += "-C ${WEBOS_BUILD_DIR} -f Makefile.WebKitSupplemental"

export QTDIR = "${WORKDIR}/qt4-webos"

do_configure() {
    # Don't trust incremental configures
    rm -rf ${WEBOS_BUILD_DIR}
    
    # .qmake.cache is not part of qt4-webos checkout, so let's try to create fake one, pointing to your stored stuff
    mkdir -p "${QTDIR}"
    echo "QT_SOURCE_TREE = \$\$quote(${STAGING_DIR_HOST}/usr/src/qt4-webos/git)" > ${QTDIR}/.qmake.cache
    echo "QT_BUILD_TREE = \$\$quote(${STAGING_DIR_HOST}/usr/src/qt4-webos/build)" >> ${QTDIR}/.qmake.cache

    mkdir -p ${WEBOS_BUILD_DIR}
    cd ${WEBOS_BUILD_DIR}
    ${QMAKE} ../WebKitSupplemental.pro -o Makefile.WebKitSupplemental
}


do_install() {
    # Don't install directly into the sysroot
    export STAGING_INCDIR=${D}${includedir}
    export STAGING_LIBDIR=${D}${libdir}
    oe_runmake install

    install -d 766 ${D}${prefix}/plugins/platforms
    install -v -m 555 ${WEBOS_BUILD_DIR}/qbsplugin/libqbsplugin.so ${D}${prefix}/plugins/platforms/
    install -d 766 ${D}${prefix}/plugins/webkit/
    install -v -m 555 ${WEBOS_BUILD_DIR}/qtwebkitplugin/libqtwebkitplugin.so ${D}${prefix}/plugins/webkit/
    if [ -d qbsplugin/fonts ]; then
        install -d ${D}${datadir}/fonts
        install -v -m 644 -t ${D}${datadir}/fonts qbsplugin/fonts/*
    fi
}


FILES_${PN} += "${prefix}/plugins/platforms/libqbsplugin.so"
FILES_${PN} += "${prefix}/plugins/webkit/libqtwebkitplugin.so"
FILES_${PN} += "${datadir}/fonts"
FILES_${PN}-dbg += "${prefix}/plugins/platforms/.debug"
FILES_${PN}-dbg += "${prefix}/plugins/webkit/.debug"
