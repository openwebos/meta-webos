# (c) Copyright 2012  Hewlett-Packard Development Company, L.P. 

DESCRIPTION = "Qt is a versatile cross-platform application framework"
# TODO: Change GPLv3 license to LICENSE.GPL3 once it gets in Qt Github repository
LICENSE = "LGPLv2.1 | GPLv3"
LIC_FILES_CHKSUM = \
                  " file://LICENSE.LGPL;md5=77718fea3774d90f2f90dfaaba1c3d1b \
                    file://LGPL_EXCEPTION.txt;md5=411080a56ff917a5a1aa08c98acae354 \
                    file://${COMMON_LICENSE_DIR}/GPL-3.0;md5=c79ff39f19dfec6d293b95dea7b07891"
SECTION = "webos/libs"

DEPENDS = "freetype jpeg libpng zlib glib-2.0 nyx-lib"

# Please update QTDIR in webkit-supplemental.bb file with the below value(r<n>), when ever it changes
PR = "r6"

inherit autotools
inherit pkgconfig
inherit webos_submissions

def qt4_machine_config_flags(bb, d):
    if bb.data.getVar('MACHINE', d, True):
        this_machine = bb.data.getVar('MACHINE', d, 1)

        if this_machine == "opal" or this_machine == "topaz":
            return "-xplatform qws/linux-armv6-g++ -opengl -plugin-gfx-egl -DQT_QWS_CLIENTBLIT -DPALM_DEVICE -qconfig palm"
        elif this_machine == "qemux86":
            return "-xplatform qws/linux-qemux86-g++ -no-neon -no-rpath -DPALM_DEVICE -qconfig palm"
        elif this_machine == "qemuarm":
            return "-xplatform qws/linux-armv6-g++ -no-opengl -DQT_QWS_CLIENTBLIT -no-neon -DPALM_DEVICE -qconfig palm"
        else:
            return "-xplatform qws/linux-armv6-g++ -DQT_QWS_CLIENTBLIT -no-neon -DPALM_DEVICE -qconfig palm"
    else:
        return ""

def qt4_machine_config_arch_lite(bb, d):
    if bb.data.getVar('MACHINE', d, True):
        this_machine = bb.data.getVar('MACHINE', d, 1)

        if this_machine == "qemux86":
            return "-embedded x86"
        elif this_machine == "qemuarm":
            return "-arch arm -embedded"
        else:
            return "-arch arm -qpa"
    else:
        return ""

def qt4_machine_config_arch_lite_qpa(bb, d):
    if bb.data.getVar('MACHINE', d, True):
        this_machine = bb.data.getVar('MACHINE', d, 1)

        if this_machine == "qemux86":
            return "-qpa"
        elif this_machine == "qemuarm":
            return "-arch arm -qpa"
        else:
            return "-arch arm -qpa"
    else:
        return ""

WEBOS_GIT_TAG = "submissions/${WEBOS_SUBMISSION}"
SRC_URI = "${OPENWEBOS_GIT_REPO}/qt;tag=${WEBOS_GIT_TAG};protocol=git"
S = "${WORKDIR}/git"

PALM_BUILD_DIR = "${S}/../qt-build-${MACHINE}"

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
# Export the current configuration out so that Qt .pro files can utilize these during
# their configuration
export WEBOS_CONFIG="webos ${MACHINE}"

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

# Turn off PostgreSQL and MySQL.
# QT is a combination of native and target (cross) build and the "unset"
# statements above and calling QT_CONFIG_FLAGS using _NATIVE and _HOST dirs
# below enables it to find the SQL headers on the host.
QT_CONFIG_FLAGS = "${@qt4_machine_config_arch_lite_qpa(bb, d)} -little-endian \
                   -release -opensource -confirm-license \
                   -no-cups -no-nis -no-exceptions \
                   -no-accessibility -no-qt3support -no-xmlpatterns -no-multimedia -no-phonon -no-phonon-backend \
                   -no-svg -no-webkit -no-javascript-jit -no-scripttools -no-dbus -no-sql-sqlite -no-sql-psql -no-sql-mysql\
                   -no-libtiff -no-libmng -no-gstreamer -no-audio-backend -no-gtkstyle \
                   -reduce-relocations -reduce-exports -force-pkg-config -glib -qt-zlib -system-freetype -qt-kbd-linuxinput \
                   --bindir=${STAGING_BINDIR_NATIVE} --prefix=${STAGING_DIR_HOST} \
                   -make 'libs' \
                   ${@qt4_machine_config_flags(bb, d)}"

do_configure() {
    mkdir -p ${PALM_BUILD_DIR}
    cd ${PALM_BUILD_DIR}
    ${S}/configure -v ${QT_CONFIG_FLAGS}
    # We want the shared libraries to have an SONAME records => remove the empty -Wl,-soname,
    # argument that qmake adds (why is it doing this?).
    find . -name Makefile | xargs sed -i -e 's/-Wl,-soname, //' -e 's/-Wl,-soname,$//'
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
    install -v -m 555 ${PALM_BUILD_DIR}/plugins/imageformats/*.so ${D}/usr/plugins/imageformats

    if [ "${MACHINE}" != "qemux86" -o "${MACHINE}" != "qemuarm"]; then
        oe_libinstall -C ${PALM_BUILD_DIR}/lib/ -so libQtOpenGL ${D}/usr/lib
    fi
    export STAGING_INCDIR="${STAGING_INCDIR}"
    export STAGING_LIBDIR="${STAGING_LIBDIR}"
    cd ${PALM_BUILD_DIR}

    oe_runmake install
    install -v -m 644 ${S}/src/opengl/gl2paintengineex/qglcustomshaderstage_p.h ${STAGING_INCDIR}/QtOpenGL

    # Install the pkgconfigs for the Qt libraries in the correct place.
    install -d ${D}/usr/lib/pkgconfig

    # Fix up the generated .pc files so that they have the sysroot-relative 
    # settings that OE-core expects. Note that qmake configures the Makefiles
    # to install files under the root of the sysroot instead of under the more
    # usual /usr -- hence the corrected prefix is empty and includedir is /include.
    # However, in order for pkg-config to find the files, they have to be under
    # /usr, so that's where the modified ones are written.
    for pc in ${STAGING_DIR_HOST}/lib/pkgconfig/Qt*.pc; do
        outf=${D}/usr/lib/pkgconfig/$(basename $pc)
        echo "Fix up $pc -> $outf"
        sed -e 's:^prefix=.*$:prefix=:' \
               -e 's:^includedir=.*:includedir=/include:' \
               -e '/^Libs\.private:/ s: -L/[^ ]* : -L@{libdir} :g' \
               -e '/^Cflags:/ s: -I/[^ ]* : :g' $pc \
           | tr @ '$' > $outf
    done
}

do_install_append() {
    oe_libinstall -C ${PALM_BUILD_DIR}/lib/ -so libQtSql ${D}/usr/lib
    oe_libinstall -C ${PALM_BUILD_DIR}/lib/ -so libQtDeclarative ${D}/usr/lib
    oe_libinstall -C ${PALM_BUILD_DIR}/lib/ -so libQtScript ${D}/usr/lib

    oe_libinstall -C ${PALM_BUILD_DIR}/plugins/platforms -so libqpalm ${D}/usr/lib

    install -d ${D}/usr/plugins/platforms
    install -v -m 555 ${PALM_BUILD_DIR}/plugins/platforms/*.so ${D}/usr/plugins/platforms/

    install -v -m 555 ${PALM_BUILD_DIR}/plugins/platforms/libqpalm.so ${STAGING_LIBDIR}/libqpalm.so

    if [ "${MACHINE}" = "opal" -o "${MACHINE}" = "topaz" ]; then
        install -d ${D}/usr/plugins/imports/Qt/labs/shaders
        install -v -m 555 ${PALM_BUILD_DIR}/imports/Qt/labs/shaders/* ${D}/usr/plugins/imports/Qt/labs/shaders/
    fi
}

FILES_${PN} += "/usr/plugins"
FILES_${PN}-dbg += "/usr/plugins/gfxdrivers/.debug"
FILES_${PN}-dbg += "/usr/plugins/imageformats/.debug"
FILES_${PN}-dbg += "/usr/plugins/platforms/.debug"
FILES_${PN}-dbg += "/usr/plugins/imageformats/.debug"
FILES_${PN}-dbg += "/usr/plugins/imports/Qt/labs/shaders/.debug"
