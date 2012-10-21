# (c) Copyright 2012  Hewlett-Packard Development Company, L.P. 

SUMMARY = "Open webOS edition of the Qt4 cross-platform application framework"
SECTION = "libs"
# TODO: Change GPLv3 license to LICENSE.GPL3 once it gets in Qt Github repository
LICENSE = "LGPLv2.1 | GPLv3"
LIC_FILES_CHKSUM = \
                  " file://LICENSE.LGPL;md5=77718fea3774d90f2f90dfaaba1c3d1b \
                    file://LGPL_EXCEPTION.txt;md5=411080a56ff917a5a1aa08c98acae354 \
                    file://${COMMON_LICENSE_DIR}/GPL-3.0;md5=c79ff39f19dfec6d293b95dea7b07891"

inherit webos_public_repo
inherit webos_oe_runmake_no_env_override
inherit webos_submissions
inherit webos_library

WEBOS_GIT_TAG = "submissions/${WEBOS_SUBMISSION}"
SRC_URI = "${OPENWEBOS_GIT_REPO}/qt;tag=${WEBOS_GIT_TAG};protocol=git \
           file://0001-configure-revert-palm-change-to-force-i386-HOST-on-x.patch \
           file://0002-webkit2-set-OUTPUT_DIR-value-if-empty.patch \
"
S = "${WORKDIR}/git"

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
