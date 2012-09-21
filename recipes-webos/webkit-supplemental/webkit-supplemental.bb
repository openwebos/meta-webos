# Copyright 2012  Hewlett-Packard Development Company, L.P.

DESCRIPTION = "webOS webkit additional features. This is a supplemental component to webkit"
LICENSE = "Apache-2.0"
SECTION = "webos/libs"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"
DEPENDS = "qt4-webos webkit-webos"
PR = "r0"

inherit autotools
inherit webos_public_repo
inherit webos_submissions

WEBOS_GIT_TAG = "${WEBOS_SUBMISSION}"
SRC_URI = "${ISIS-PROJECT_GIT_REPO}/WebKitSupplemental;tag=${WEBOS_GIT_TAG};protocol=git"
S = "${WORKDIR}/git"

PALM_CC_OPT = "-O2"

export STRIP_TMP="${STRIP}"
export F77_TMP="${F77}"
export QMAKE_MKSPEC_PATH_TMP="${QMAKE_MKSPEC_PATH}"
export CC_TMP="${CC}"
export CPPFLAGS_TMP="${CPPFLAGS}"
export RANLIB_TMP="${RANLIB}"
export CXX_TMP="${CXX}"
export OBJCOPY_TMP="${OBJCOPY}"
export CCLD_TMP="${CCLD}"
export CFLAGS_TMP="${CFLAGS}"
export TARGET_LDFLAGS_TMP="${TARGET_LDFLAGS}"
export LDFLAGS_TMP="${LDFLAGS}"
export AS_TMP="${AS}"
export AR_TMP="${AR} r"
export CPP_TMP="${CPP}"
export TARGET_CPPFLAGS_TMP="${TARGET_CPPFLAGS}"
export CXXFLAGS_TMP="${CXXFLAGS}"
export OBJDUMP_TMP="${OBJDUMP}"
export LD_TMP="${LD}"
export QMAKE="${STAGING_BINDIR_NATIVE}/qmake-palm"
# The QTDIR needs to be changed everytime qt4-webos recipe revision number(PR) changes
# Also, it's known to cause problems when shared state for qt4-webos is used instead of a fresh build
export QTDIR="${WORKDIR}/../qt4-webos-${PREFERRED_VERSION_qt4-webos}-r3/qt-build-${MACHINE}"

do_configure() {
    export STAGING_DIR="${STAGING_DIR}"
    export STAGING_INCDIR="${STAGING_INCDIR}"
    export STAGING_LIBDIR="${STAGING_LIBDIR}"
    export TARGET_ARCH=${TARGET_ARCH}
}

do_compile() {
    export CXX_TMP="${CXX}"
    export STAGING_DIR="${STAGING_DIR}"
    export STAGING_INCDIR="${STAGING_INCDIR}"
    export STAGING_LIBDIR="${STAGING_LIBDIR}"
    export TARGET_ARCH=${TARGET_ARCH}
    export MOC="${STAGING_BINDIR_NATIVE}/moc-palm"
    mkdir -p build-${MACHINE}
    cd build-${MACHINE}
    $QMAKE ../WebKitSupplemental.pro -o Makefile.WebKitSupplemental
    make -f Makefile.WebKitSupplemental
}

do_install() {
    export CXX_TMP="${CXX}"
    export STAGING_DIR="${STAGING_DIR}"
    export STAGING_INCDIR="${STAGING_INCDIR}"
    export STAGING_LIBDIR="${STAGING_LIBDIR}"
    export TARGET_ARCH=${TARGET_ARCH}
    export MOC="${STAGING_BINDIR_NATIVE}/moc-palm"
    cd build-${MACHINE}
    make -f Makefile.WebKitSupplemental install

    install -d 766 ${D}${prefix}/plugins/platforms
    install -m 555 qbsplugin/libqbsplugin.so ${D}${prefix}/plugins/platforms/
    install -d 766 ${D}${prefix}/plugins/webkit/
    install -m 555 qtwebkitplugin/libqtwebkitplugin.so ${D}${prefix}/plugins/webkit/
    if [ -d ../qbsplugin/fonts ]; then
        install -d ${D}${datadir}/fonts
        install -m 644 -t ${D}${datadir}/fonts ../qbsplugin/fonts/*
    fi
}

FILES_${PN} += "${prefix}/plugins/platforms/libqbsplugin.so"
FILES_${PN} += "${prefix}/plugins/webkit/libqtwebkitplugin.so"
FILES_${PN} += "${datadir}/fonts"
FILES_${PN}-dbg += "${prefix}/plugins/platforms/.debug"
FILES_${PN}-dbg += "${prefix}/plugins/webkit/.debug"
