# Copyright (c) 2013 Hewlett-Packard Development Company, L.P.
# Copyright (c) 2013 LG Electronics, Inc.

inherit qmake5

# These are used in the luna-sysmgr recipe
export QT_CONFIGURE_PREFIX_PATH = "${STAGING_EXECPREFIXDIR}"
export QT_CONFIGURE_HEADERS_PATH = "${STAGING_INCDIR}/${QT_STAGING}"
export QT_CONFIGURE_LIBRARIES_PATH = "${STAGING_LIBDIR}/${QT_STAGING}"
export QT_CONFIGURE_BINARIES_PATH = "${STAGING_BINDIR_NATIVE}/${QT_STAGING}"

# This is used in the webappmanager recipes
export STAGING_INCDIR

# Set webOS specific locations for .pr* files to access
EXTRA_QMAKEVARS_PRE += "WEBOS_STAGING_INCDIR=${STAGING_INCDIR}"
EXTRA_QMAKEVARS_PRE += "WEBOS_INSTALL_QML=${libdir}/${QT_DIR_NAME}/qml"
EXTRA_QMAKEVARS_PRE += "WEBOS_INSTALL_LIBS=${libdir}"
EXTRA_QMAKEVARS_PRE += "WEBOS_INSTALL_BINS=${bindir}"
EXTRA_QMAKEVARS_PRE += "WEBOS_INSTALL_HEADERS=${includedir}/"
