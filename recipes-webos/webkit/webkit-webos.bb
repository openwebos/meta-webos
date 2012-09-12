# Copyright 2012  Palm, Inc.  All rights reserved.

DESCRIPTION = "webOS WebKit is an open source web rendering engine."
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM =  "file://Source/WebCore/LICENSE-LGPL-2.1;md5=a778a33ef338abbaf8b8a7c36b6eec80"
DEPENDS = "qt4-webos luna-service2 sqlite3"

inherit autotools
inherit webos_submissions

PR = "r4"

#
# Webkit source is identified WEBOS_SUBMISSION and SRCREV defined in
# webos-component-submission.inc & webos-component-head.inc. Those 
# values needs adjustment for future updates. 
#
SRC_URI = "${ISIS_PROJECT_DOWNLOAD}/WebKit/WebKit_${WEBOS_SUBMISSION}s.zip"
S = "${WORKDIR}/isis-project-WebKit-${SRCREV}"

SRC_URI[md5sum] = "ed3995d6dd54a81c4db5d9ea92f30ef4"
SRC_URI[sha256sum] = "7835d2cb953724f67670e421a614022767865fa9bfc52f8bdccbc95605be8b4e"

# XXX Expediently patch Tools/Scripts/webkitdirs.pm to remove the
# extraneous empty "-W1,-soname," that qmake adds to link command lines.
# Eventually, arrange for qmake to do the right thing.
SRC_URI += "file://remove-empty-soname-arg.patch"

#EXTRA_OEMAKE = "MACHINE=${MACHINE} DISTRO_TYPE=${DISTRO_TYPE} TARGET_ARCH=${TARGET_ARCH}"
PALM_CC_OPT = "-O2"
OBJDIR = "${MACHINE}-${TARGET_ARCH}"

WEBKITOUTPUTDIR = "${S}/WebKitBuild/${OBJDIR}"
PALM_BUILD_DIR = "${WEBKITOUTPUTDIR}/Release"

CXXFLAGS += "-Wl,-rpath-link,${OPIEDIR}/lib"
CPPFLAGS += "-Wl,-rpath-link,${OPIEDIR}/lib"

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
export AR_TMP="${AR}"
export CPP_TMP="${CPP}"
export TARGET_CPPFLAGS_TMP="${TARGET_CPPFLAGS}"
export CXXFLAGS_TMP="${CXXFLAGS}"
export OBJDUMP_TMP="${OBJDUMP}"
export LD_TMP="${LD}"
export QTDIR = "${WORKDIR}/../qt4-webos-*/git"

do_configure() {
:
}

do_compile() {
    export STAGING_INCDIR="${STAGING_INCDIR}"
    export STAGING_LIBDIR="${STAGING_LIBDIR}"
#    export MACHINE=${MACHINE}

    QMAKE_LINK_ARGS=""

#    if [ "${MACHINE}" = "opal" -o "${MACHINE}" = "topaz" ]; then
#        QMAKE_LINK_ARGS='--qmakearg="QMAKE_LINK=\"${CXX} -B ${STAGING_BINDIR_NATIVE}/../libexec/gcc/arm-none-linux-gnueabi/4.5.1/gold -Wl,-debug\""'
#    fi

        WEBKITOUTPUTDIR=${WEBKITOUTPUTDIR} ${S}/Tools/Scripts/build-webkit --qt \
        --release \
        --no-video \
        --no-webgl \
        --only-webkit \
        --no-webkit2 \
        --qmake="${STAGING_BINDIR_NATIVE}/qmake-palm" \
        --makeargs="${PARALLEL_MAKE}" \
        --qmakearg="DEFINES+=PALM_DEVICE" \
        --qmakearg="DEFINES+=ENABLE_PALM_SERVICE_BRIDGE=1" \
        --qmakearg="QMAKE_AR=\"${AR} r\"" \
        ${QMAKE_LINK_ARGS}
}

do_install() {
    install -d ${STAGING_INCDIR}/QtWebKit

    libqtwebkit_3ver=$(basename ${PALM_BUILD_DIR}/lib/libQtWebKit.so.*.*.*)
    if [ ! -r "${PALM_BUILD_DIR}/lib/$libqtwebkit_3ver" ]; then
        echo ERROR: '${PALM_BUILD_DIR}/lib/$libqtwebkit_3ver' did not expand or expanded to multiple files.
        return 1
    fi
    libqtwebkit_2ver=$(echo $libqtwebkit_3ver | awk -F. '{ print $1 "." $2 "." $3 "." $4 }')
    libqtwebkit_1ver=$(echo $libqtwebkit_3ver | awk -F. '{ print $1 "." $2 "." $3 }')

    install -m 555 ${PALM_BUILD_DIR}/lib/$libqtwebkit_3ver ${STAGING_LIBDIR}
    ln -sf $libqtwebkit_3ver ${STAGING_LIBDIR}/$libqtwebkit_2ver
    ln -sf $libqtwebkit_3ver ${STAGING_LIBDIR}/$libqtwebkit_1ver
    ln -sf $libqtwebkit_3ver ${STAGING_LIBDIR}/libQtWebKit.so


    # WebKit stages header files that include other header files. We can't just
    # copy their staged header files because the path won't be correct so we
    # have to copy the actual header file that is referenced.
    install -m 444 ${PALM_BUILD_DIR}/include/QtWebKit/Q* ${STAGING_INCDIR}/QtWebKit
    cd ${PALM_BUILD_DIR}/include/QtWebKit && perl -e 'while (<>) {if (m/^#include "([^"]+)"/) {print `install -m 444 $1 ${STAGING_INCDIR}/QtWebKit`;}}' q*.h
    install -d ${D}${libdir}
    oe_libinstall -C ${PALM_BUILD_DIR}/lib -so libQtWebKit ${D}/${libdir}

    install -d ${D}/usr/plugins/imports/QtWebKit
    install -m 555 ${PALM_BUILD_DIR}/imports/QtWebKit/* ${D}/usr/plugins/imports/QtWebKit
}

do_makeclean() {
:
}

do_clean() {
    if [ -d ${WEBKITOUTPUTDIR} ]; then
        WEBKITOUTPUTDIR=${WEBKITOUTPUTDIR} ${S}/Tools/Scripts/build-webkit --qt \
            --release \
            --clean \
            --qmake="${STAGING_BINDIR_NATIVE}/qmake-palm"

        rm -rf ${WEBKITOUTPUTDIR}
    fi
}

FILES_${PN} += "${libdir}/libQtWebKit.so*"
FILES_${PN} += "/usr/plugins/imports/QtWebKit/qmldir"
FILES_${PN} += "/usr/plugins/imports/QtWebKit/libqmlwebkitplugin.so"
FILES_${PN}-dbg += "${libdir}/.debug"
FILES_${PN}-dbg += "/usr/plugins/imports/QtWebKit/.debug"

