# (c) Copyright 2013  Hewlett-Packard Development Company, L.P.
# (c) Copyright 2013  LG Electronics

inherit qmake5

export QT_DIR_NAME = "qt5"
export QT_CONF_PATH = "${WORKDIR}/qt.conf"

# These are used in the luna-sysmgr recipe
export QT_CONFIGURE_PREFIX_PATH = "${STAGING_EXECPREFIXDIR}"
export QT_CONFIGURE_HEADERS_PATH = "${STAGING_INCDIR}/${QT_STAGING}"
export QT_CONFIGURE_LIBRARIES_PATH = "${STAGING_LIBDIR}/${QT_STAGING}"
export QT_CONFIGURE_BINARIES_PATH = "${STAGING_BINDIR_NATIVE}/${QT_STAGING}"

# This is used in the webappmanager recipes
export STAGING_INCDIR

do_generate_qt_config_file() {

    # Set webOS specific locations for .pr* files to access
    ${OE_QMAKE_QMAKE} -set WEBOS_STAGING_INCDIR ${STAGING_INCDIR}
    ${OE_QMAKE_QMAKE} -set WEBOS_INSTALL_QML ${libdir}/qt5/qml
    ${OE_QMAKE_QMAKE} -set WEBOS_INSTALL_LIBS ${libdir}
    ${OE_QMAKE_QMAKE} -set WEBOS_INSTALL_BINS ${bindir}
    ${OE_QMAKE_QMAKE} -set WEBOS_INSTALL_HEADERS ${includedir}/

    cat > ${QT_CONF_PATH} <<EOF
[Paths]
Binaries = ${bindir}
Headers = ${includedir}/${QT_DIR_NAME}
Plugins = ${libdir}/${QT_DIR_NAME}/plugins
Libraries = ${libdir}
LibraryExecutables = ${libexecdir}
Imports = ${libdir}/${QT_DIR_NAME}/imports
Qml2Imports = ${libdir}/${QT_DIR_NAME}/qml
Documentation = ${docdir}/${QT_DIR_NAME}
Data = ${datadir}/${QT_DIR_NAME}
Sysroot = ${STAGING_DIR_TARGET}
HostData = ${STAGING_DATADIR}/${QT_DIR_NAME}
HostBinaries = ${STAGING_BINDIR_NATIVE}
EOF

}

# See meta-qt5/qmake5_base.bbclass for more info
do_generate_qt_config_file[depends] += "qt5-native:do_populate_sysroot"
