# (c) Copyright 2012  Hewlett-Packard Development Company, L.P. 

require qt4-palm.inc

LICENSE = "LGPLv2.1 | GPLv3"
LIC_FILES_CHKSUM = \ 
                  " file://LICENSE.LGPL;md5=77718fea3774d90f2f90dfaaba1c3d1b \
                    file://LGPL_EXCEPTION.txt;md5=411080a56ff917a5a1aa08c98acae354 \
		    file://${COMMON_LICENSE_DIR}/GPL-3.0;md5=c79ff39f19dfec6d293b95dea7b07891" 
# TODO: Change GPL-3.0 license to LICENSE.GPL3 once it gets in Qt Github repository

SECTION = "libs"
DEPENDS += "freetype jpeg libpng zlib glib-2.0 nyx-lib"

inherit autotools

PR = "0.33"
SRC_URI = "${OPENWEBOS_GIT_REPO}/qt;tag=${PR};protocol=git \
           file://qpixmapatlascpp.patch"
S = "${WORKDIR}/git"

PALM_BUILD_DIR = "${S}/../qt-build-${MACHINE}"

# temporary workaround for https://jira.palm.com/browse/NOV-129865
#CMP_IGNORES += "${WORKDIR}*/palm/submissions/qt-build*/*/Makefile"

# staging is nondeterministic
#CMP_IGNORES += "${T}/log.do_stage"

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

do_configure_prepend() {
    # clear out the staging folder
    rm -fr ${STAGING_INCDIR}/Qt
    rm -fr ${STAGING_INCDIR}/QtCore
    rm -fr ${STAGING_INCDIR}/QtGui
    rm -fr ${STAGING_INCDIR}/QtNetwork
    rm -fr ${STAGING_INCDIR}/QtOpenGL
    rm -fr ${STAGING_INCDIR}/QtSql
    rm -fr ${STAGING_INCDIR}/QtTest
    rm -fr ${STAGING_INCDIR}/QtXml

    rm -f ${STAGING_LIBDIR}/libQt*

    # Exporting these variables here so that ./configure knows about them when parsing qmake.conf
    export STAGING_INCDIR="${STAGING_INCDIR}"
    export STAGING_LIBDIR="${STAGING_LIBDIR}"

    unset STRIP
    unset F77
    unset QMAKE_MKSPEC_PATH
    unset CC
    unset CPPFLAGS
    unset RANLIB
    unset CXX
    unset OBJCOPY
    unset CCLD
    unset CFLAGS
    unset TARGET_LDFLAGS
    unset LDFLAGS
    unset AS
    unset AR
    unset CPP
    unset TARGET_CPPFLAGS
    unset CXXFLAGS
    unset OBJDUMP
    unset LD
}

QT_CONFIG_FLAGS = "${@qt4_machine_config_arch_lite(bb, d)} -little-endian \
                   -release -opensource -confirm-license \
                   -no-cups -no-nis -no-exceptions \
                   -no-accessibility -no-qt3support -no-xmlpatterns -no-multimedia -no-phonon -no-phonon-backend \
                   -no-svg -no-webkit -no-javascript-jit -no-scripttools -no-dbus -no-sql-sqlite\
                   -no-libtiff -no-libmng -no-gstreamer -no-audio-backend -no-gtkstyle \
                   -reduce-relocations -reduce-exports -force-pkg-config -glib -qt-zlib -system-freetype -qt-kbd-linuxinput \
                   --bindir=${STAGING_BINDIR_NATIVE} --prefix=${STAGING_DIR_HOST} \
                   -make 'libs' \
                   ${@qt4_machine_config_flags(bb, d)}"

do_configure() {
    mkdir -p ${PALM_BUILD_DIR}
    cd ${PALM_BUILD_DIR}
    ${S}/configure -v ${QT_CONFIG_FLAGS}
}

do_compile_prepend() {
    export STAGING_INCDIR="${STAGING_INCDIR}"
    export STAGING_LIBDIR="${STAGING_LIBDIR}"
    cd ${PALM_BUILD_DIR}
}

do_install() {
    export STAGING_INCDIR="${STAGING_INCDIR}"
    export STAGING_LIBDIR="${STAGING_LIBDIR}"
    cd ${PALM_BUILD_DIR}

    install -d ${D}/usr/lib
    oe_libinstall -C ${PALM_BUILD_DIR}/lib/ -so libQtCore ${D}/usr/lib
    oe_libinstall -C ${PALM_BUILD_DIR}/lib/ -so libQtGui ${D}/usr/lib
    oe_libinstall -C ${PALM_BUILD_DIR}/lib/ -so libQtNetwork ${D}/usr/lib
    oe_libinstall -C ${PALM_BUILD_DIR}/lib/ -so libQtXml ${D}/usr/lib

    install -d ${D}/usr/plugins
    install -d ${D}/usr/plugins/imageformats
    install -m 555 ${PALM_BUILD_DIR}/plugins/imageformats/*.so ${D}/usr/plugins/imageformats

    if [ "${MACHINE}" != "qemux86" || "${MACHINE}" != "qemuarm"]; then
        oe_libinstall -C ${PALM_BUILD_DIR}/lib/ -so libQtOpenGL ${D}/usr/lib
    fi
    export STAGING_INCDIR="${STAGING_INCDIR}"
    export STAGING_LIBDIR="${STAGING_LIBDIR}"
    cd ${PALM_BUILD_DIR}

    oe_runmake install
    install -m 644 ${S}/src/opengl/gl2paintengineex/qglcustomshaderstage_p.h ${STAGING_INCDIR}/QtOpenGL
}

do_install_append() {
    oe_libinstall -C ${PALM_BUILD_DIR}/lib/ -so libQtDeclarative ${D}/usr/lib
    oe_libinstall -C ${PALM_BUILD_DIR}/lib/ -so libQtScript ${D}/usr/lib
    oe_libinstall -C ${PALM_BUILD_DIR}/lib/ -so libQtSql ${D}/usr/lib

    if [ "${MACHINE}" = "opal" -o "${MACHINE}" = "topaz" ]; then
        oe_libinstall -C ${PALM_BUILD_DIR}/plugins/platforms -so libqpalm ${D}/usr/lib
#        oe_libinstall -C ${PALM_BUILD_DIR}/plugins/platforms -so libqwebos ${D}/usr/lib

        install -d ${D}/usr/plugins/platforms
        install -m 555 ${PALM_BUILD_DIR}/plugins/platforms/*.so ${D}/usr/plugins/platforms/

        install -m 555 ${PALM_BUILD_DIR}/plugins/platforms/libqpalm.so ${STAGING_LIBDIR}/libqpalm.so
#        install -m 555 ${PALM_BUILD_DIR}/plugins/platforms/libqwebos.so ${STAGING_LIBDIR}/libqwebos.so

        install -d ${D}/usr/plugins/imports/Qt/labs/shaders
        install -m 555 ${PALM_BUILD_DIR}/imports/Qt/labs/shaders/* ${D}/usr/plugins/imports/Qt/labs/shaders/
    fi
}

FILES_${PN} += "/usr/plugins"
FILES_${PN}-dbg += "/usr/plugins/gfxdrivers/.debug"
FILES_${PN}-dbg += "/usr/plugins/imageformats/.debug"
FILES_${PN}-dbg += "/usr/plugins/platforms/.debug"
FILES_${PN}-dbg += "/usr/plugins/imageformats/.debug"
FILES_${PN}-dbg += "/usr/plugins/imports/Qt/labs/shaders/.debug"
