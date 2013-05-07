# Copyright (c) 2013 Hewlett-Packard Development Company, L.P.
# Copyright (c) 2013 LG Electronics, Inc.

inherit qmake5

# These are used in the luna-sysmgr recipe
export QT_CONFIGURE_PREFIX_PATH = "${OE_QMAKE_PATH_PREFIX}"
export QT_CONFIGURE_HEADERS_PATH = "${OE_QMAKE_PATH_QT_HEADERS}"
export QT_CONFIGURE_LIBRARIES_PATH = "${OE_QMAKE_PATH_LIBS}"
export QT_CONFIGURE_BINARIES_PATH = "${OE_QMAKE_PATH_EXTERNAL_HOST_BINS}"

# This is used in the webappmanager recipes
export STAGING_INCDIR

# Set webOS specific locations for .pr* files to access
EXTRA_QMAKEVARS_PRE += "WEBOS_STAGING_INCDIR=${STAGING_INCDIR}"
EXTRA_QMAKEVARS_PRE += "WEBOS_INSTALL_QML=${OE_QMAKE_PATH_QML}"
EXTRA_QMAKEVARS_PRE += "WEBOS_INSTALL_LIBS=${libdir}"
EXTRA_QMAKEVARS_PRE += "WEBOS_INSTALL_BINS=${bindir}"
EXTRA_QMAKEVARS_PRE += "WEBOS_INSTALL_HEADERS=${includedir}/"
