# (c) Copyright 2012  Hewlett-Packard Development Company, L.P. 

SUMMARY = "Open webOS edition of the Qt4 cross-platform application framework"
SECTION = "libs"
# TODO: Change GPLv3 license to LICENSE.GPL3 once it gets in Qt Github repository
LICENSE = "LGPLv2.1 | GPLv3"
LIC_FILES_CHKSUM = \
                  " file://LICENSE.LGPL;md5=77718fea3774d90f2f90dfaaba1c3d1b \
                    file://LGPL_EXCEPTION.txt;md5=411080a56ff917a5a1aa08c98acae354 \
                    file://${COMMON_LICENSE_DIR}/GPL-3.0;md5=c79ff39f19dfec6d293b95dea7b07891"

# XXX We don't depend on qmake-webos-native because we continue to build qmake-palm during
# do_configure() -- see commentary in qmake-webos-native.bb
DEPENDS = "freetype jpeg libpng zlib glib-2.0 nyx-lib"

PR = "r13"

inherit webos_public_repo
inherit webos_oe_runmake_no_env_override
inherit webos_submissions
inherit webos_library

inherit webos_machine_dep

QT4_MACHINE_CONFIG_ARCH_LITE_QPA = "-qpa"
QT4_MACHINE_CONFIG_ARCH_LITE_QPA_arm = "-arch arm -qpa"
QT4_MACHINE_CONFIG_FLAGS = "-xplatform qws/linux-armv6-g++ -no-opengl -no-neon -no-rpath -DPALM_DEVICE -qconfig palm"
QT4_MACHINE_CONFIG_FLAGS_x86 = "-xplatform qws/linux-qemux86-g++ -no-neon -no-rpath -DPALM_DEVICE -qconfig palm"
QT4_MACHINE_CONFIG_FLAGS_armv7a = "-xplatform qws/linux-armv6-g++ -opengl -plugin-gfx-egl -DPALM_DEVICE -qconfig palm"

WEBOS_GIT_TAG = "submissions/${WEBOS_SUBMISSION}"
SRC_URI = "${OPENWEBOS_GIT_REPO}/qt;tag=${WEBOS_GIT_TAG};protocol=git"
S = "${WORKDIR}/git"

PALM_BUILD_DIR = "${S}/../qt-build-${MACHINE}"
QT4_STAGING_BUILD_DIR = "/usr/src/qt4-webos"

EXTRA_OEMAKE += "-C ${PALM_BUILD_DIR}"

# Exporting these variables since they appear in our qmake.conf
export STAGING_INCDIR
export STAGING_LIBDIR

# Our qmake.conf files for the target builds use these variables to set QMAKE_*
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
# The -separate-debug-info configure option doesn't appear to work, so make strip into a NOP
export STRIP_TMP=":"

# Export the current configuration out so that Qt .pro files can utilize these during
# their configuration
export WEBOS_CONFIG="webos ${MACHINE}"


# Unset these so that when ./configure-webos builds qmake, it's a native build.
use_native_toolchain () {
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
    unset STRIP
}

# Turn off PostgreSQL and MySQL.
# QT is a combination of native and target (cross) build and the "unset"
# statements above enables it to find the SQL headers on the host.
#
# Need to specify --datadir because its default is the --prefix setting. Also, it must be under
# ${libdir} since mkspecs is MACHINE-dependent.
QT_CONFIG_FLAGS = "${QT4_MACHINE_CONFIG_ARCH_LITE_QPA} -little-endian \
                   -release -opensource -confirm-license \
                   -no-cups -no-nis -no-exceptions \
                   -no-accessibility -no-qt3support -no-xmlpatterns -no-multimedia -no-phonon -no-phonon-backend \
                   -no-svg -no-webkit -no-javascript-jit -no-scripttools -no-dbus -no-sql-sqlite -no-sql-psql -no-sql-mysql \
                   -no-libtiff -no-libmng -no-gstreamer -no-audio-backend -no-gtkstyle \
                   -reduce-relocations -reduce-exports -force-pkg-config -glib -qt-zlib -system-freetype -qt-kbd-linuxinput \
                   -prefix ${prefix} -datadir ${libdir}/qmake-webos \
                   -make 'libs' \
                   ${QT4_MACHINE_CONFIG_FLAGS}"

do_configure() {
    # Since configure builds qmake, we have to modify it to generate the src/corelib/global/qconfig.cpp we want (instead of
    # modifying it after configure has run, which would have been cleaner). Also, configure assumes that it resides in the
    # root of the source tree, so the modified one can't be placed in PALM_BUILDDIR (=> XXX need to add configure-webos.sh
    # to .gitignore).
    sed -e '/QT_CONFIGURE_LICENSEE/ s/^/#include <stdlib.h>\n/' \
        -e '/QT_CONFIGURE_[^_]*_PATH/ {; s/\(QT_CONFIGURE_[^_]*_PATH\)/\1 (getenv("\1") ? getenv("\1") : (/; s/;/ ));/; }' \
            configure > configure-webos.sh

    # Don't trust incremental configures
    rm -rf ${PALM_BUILD_DIR}

    mkdir -p ${PALM_BUILD_DIR}
    cd ${PALM_BUILD_DIR}
    use_native_toolchain
    sh ${S}/configure-webos.sh -v ${QT_CONFIG_FLAGS}

    # We want the shared libraries to have an SONAME records => remove the empty -Wl,-soname,
    # argument that qmake adds (why is it doing this?).
    find . -name Makefile | xargs sed -i -e 's/-Wl,-soname, //' -e 's/-Wl,-soname,$//'
}


do_install() {
    # Don't install directly into the sysroot
    export STAGING_INCDIR=${D}${includedir}
    export STAGING_LIBDIR=${D}${libdir}

    # Don't install qmake (already done by qmake-webos-native), but do install mkspecs,
    # since it contains a MACHINE-dependent qconfig.pri (this is because QT_CONFIG_FLAGS
    # is MACHINE-dependent). It's also target-dependent, since configure tries to
    # auto-config many of the settings.
    oe_runmake -C ${PALM_BUILD_DIR} INSTALL_ROOT=${D} install_mkspecs install_subtargets

    # Nova-Main has these additional files installed
    install -v -d ${D}${includedir}/QtOpenGL
    install -v -m 644 ${S}/src/opengl/gl2paintengineex/qglcustomshaderstage_p.h ${D}${includedir}/QtOpenGL
    if [ -d ${D}/usr/imports/Qt/labs/shaders ]; then
        install -v -d ${D}/usr/plugins/imports/Qt/labs
	    mv -v ${D}/usr/imports/Qt/labs/shaders ${D}/usr/plugins/imports/Qt/labs
    fi

    # The *_location settings in the Qt*.pc files don't make any sense since they
    # refer to native utilities => remove them.
    for pc in ${D}${libdir}/pkgconfig/Qt*.pc; do
        outf=${D}/usr/lib/pkgconfig/$(basename $pc)
        echo "Removing  _location settings from $pc"
        sed -i -e '/^[^_]*_location=/ d' $pc
    done
    
    # work around for building webkit-supplemental without qt4-webos BUILDDIR
    install -d ${D}${QT4_STAGING_BUILD_DIR}/git/src
    cp -ra ${S}/src/gui ${D}${QT4_STAGING_BUILD_DIR}/git/src
    cp -ra ${S}/src/corelib ${D}${QT4_STAGING_BUILD_DIR}/git/src

    install -d ${D}${QT4_STAGING_BUILD_DIR}/git/src/3rdparty
    cp -ra ${S}/src/3rdparty/harfbuzz ${D}${QT4_STAGING_BUILD_DIR}/git/src/3rdparty
    install -d ${D}${QT4_STAGING_BUILD_DIR}/git/src/3rdparty/webkit/
    cp -ra ${S}/src/3rdparty/webkit/include ${D}${QT4_STAGING_BUILD_DIR}/git/src/3rdparty/webkit/

    install -d ${D}${QT4_STAGING_BUILD_DIR}/build
    install -d ${D}${QT4_STAGING_BUILD_DIR}/build/src
    cp -ra ${PALM_BUILD_DIR}/include/ ${D}${QT4_STAGING_BUILD_DIR}/build
    cp -ra ${PALM_BUILD_DIR}/src/corelib ${D}${QT4_STAGING_BUILD_DIR}/build/src

    # XXX Nova-Main has libqpalm.so under ${libdir} as well as /usr/plugins because that's
    # where the link of luna-sysmgr expects to find it. luna-sysmgr will be changed by
    # [OWEBOS-2617] to look for it under /usr/plugins so that we don't have to install
    # libqpalm.so in two places. (It's a problem for it to need to be in ${libdir} because
    # the default FILES_${PN}-dev pick will pick it up from there.)

    # XXX Remove files not installed by Nova-Main. Eventually, figure out how to configure
    # so that they're not installed.
    # WARNING: This assumes that ${D} has a per-component value
    set -x
    rm -rf ${D}${datadir}
    rm -rf ${D}${bindir}
    rm -rf ${D}${libdir}/fonts
    rm -rf ${D}${prefix}/plugins/bearer
    rm -rf ${D}${prefix}/plugins/generic
    rm -rf ${D}${prefix}/plugins/inputmethods

    # XXX Can we get away with not staging /usr/lib/*.prl and ignoring the imports tree
    # (it seems as though we copy what we want from imports into plugins/imports)?
    rm -f ${D}${libdir}/*.prl
    rm -rf ${D}${prefix}/imports
    set +x
}

sysroot_stage_all_append() {
        sysroot_stage_dir ${D}${QT4_STAGING_BUILD_DIR} ${SYSROOT_DESTDIR}${QT4_STAGING_BUILD_DIR}
}

PACKAGES += "${PN}-buildsrc"

FILES_${PN} += "/usr/plugins"
# Yes, qmake-webos IS correct: mkspecs are dependent on the target.
FILES_${PN}-dev += "${libdir}/qmake-webos"
FILES_${PN}-dbg += "/usr/plugins/*/.debug"
FILES_${PN}-dbg += "/usr/plugins/imports/Qt/labs/shaders/.debug"
FILES_${PN}-buildsrc += "${QT4_STAGING_BUILD_DIR}"
