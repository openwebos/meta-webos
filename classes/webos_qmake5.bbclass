# (c) Copyright 2013  Hewlett-Packard Development Company, L.P.

inherit qmake5

export QT_DIR_NAME = "qt5"
export QT_CONF_PATH = "${WORKDIR}/qt.conf"

do_generate_qt_config_file() {

    # Set webOS specific locations for .pr* files to access
    ${OE_QMAKE_QMAKE} -set WEBOS_STAGING_INCDIR ${STAGING_INCDIR}
    ${OE_QMAKE_QMAKE} -set WEBOS_INSTALL_QML ${libdir}/qt5/qml
    ${OE_QMAKE_QMAKE} -set WEBOS_INSTALL_LIBS ${libdir}
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
