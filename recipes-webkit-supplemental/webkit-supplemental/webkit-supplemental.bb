# Copyright 2011  HP, Inc.  All rights reserved.

DESCRIPTION = "webOS webkit supplemental/additional features"
LICENSE = "Apache-2.0"
SECTION = "Linux/System"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"
DEPENDS = "qt4-palm webkit"


inherit autotools 

PALM_CC_OPT = "-O2"

SRCREV = "${AUTOREV}"
#PR = "${SUBMISSION_${PN}}"
SRC_URI = "git://github-mirror.palm.com/isis-project/WebKitSupplemental;protocol=git"
S = "${WORKDIR}/git"

EXTRA_OEMAKE = "MACHINE=${MACHINE}"

CFLAGS += "${GCOV_CXXOPTS}"
LDFLAGS += "${GCOV_LOPTS}"

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
export QTDIR="${WORKDIR}/../qt4-palm-4.8.2.02-0.33/qt-build-${MACHINE}"

do_configure() {
    export STAGING_DIR="${STAGING_DIR}"
    export STAGING_INCDIR="${STAGING_INCDIR}"
    export STAGING_LIBDIR="${STAGING_LIBDIR}"
    export TARGET_ARCH=${TARGET_ARCH}
    export QMAKE="${STAGING_BINDIR_NATIVE}/qmake-palm"

    oe_runmake configure
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

    install -d 766 ${D}/usr/plugins/platforms
    install -m 555 qbsplugin/libqbsplugin.so ${D}/usr/plugins/platforms/
    install -d 766 ${D}/usr/plugins/webkit/
    install -m 555 qtwebkitplugin/libqtwebkitplugin.so ${D}/usr/plugins/webkit/
    if [ -d ../qbsplugin/fonts ]; then
        install -d ${D}/usr/share/fonts
        install -m 644 -t ${D}/usr/share/fonts ../qbsplugin/fonts/*
    fi

}

do_populate_staging() {
}


FILES_${PN} += "/usr/plugins/platforms/libqbsplugin.so"
FILES_${PN} += "/usr/plugins/webkit/libqtwebkitplugin.so"
FILES_${PN} += "/usr/share/fonts"
FILES_${PN}-dbg += "/usr/plugins/platforms/.debug"
FILES_${PN}-dbg += "/usr/plugins/webkit/.debug"

