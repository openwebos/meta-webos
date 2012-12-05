# (c) Copyright 2012  Hewlett-Packard Development Company, L.P.
#
# webos_qmake
#
# This class is to be inherited by the recipe for every component uses
# qmake-webos-native for configuration.
#

# qmake generates Makefiles that already have the settings from the environment
# assigned to make variables.
inherit webos_oe_runmake_no_env_override

export QMAKE = "${STAGING_BINDIR_NATIVE}/qmake-palm"
export MOC = "${STAGING_BINDIR_NATIVE}/moc-palm"

# Override the compiled in defaults (which have absolute paths to the sysroots
# of the tree they were built in). Set the directories that aren't staged to
# their location in the device rootfs (if they were to be installed there)
export QT_CONFIGURE_PREFIX_PATH = "${STAGING_EXECPREFIXDIR}"
export QT_CONFIGURE_DOCUMENTATION_PATH = "${docdir}/${PN}"
export QT_CONFIGURE_HEADERS_PATH = "${STAGING_INCDIR}"
export QT_CONFIGURE_LIBRARIES_PATH = "${STAGING_LIBDIR}"
export QT_CONFIGURE_BINARIES_PATH = "${STAGING_BINDIR_NATIVE}"
# mkspecs are MACHINE-specific
export QT_CONFIGURE_DATA_PATH = "${STAGING_LIBDIR}/qmake-webos"

export QT_CONFIGURE_PLUGINS_PATH = "${webos_qtpluginsdir}"
# XXX Keep in sync with setting in qt4-webos.bb until it is modified to inherit
# from this file:
export QT_CONFIGURE_IMPORTS_PATH = "${webos_qtpluginsdir}/imports"
export QT_CONFIGURE_TRANSLATIONS_PATH = "${datadir}/${PN}/translations"
# XXX Is this used?
export QT_CONFIGURE_SETTINGS_PATH = "${localstatedir}/${PN}/settings"
export QT_CONFIGURE_EXAMPLES_PATH = "${datadir}/${PN}/examples"
export QT_CONFIGURE_DEMOS_PATH = "${libdir}/${PN}/demos"


# These appear as variables in our qmake.conf-s
export STAGING_INCDIR
export STAGING_LIBDIR
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
