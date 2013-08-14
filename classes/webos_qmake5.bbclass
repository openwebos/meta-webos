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
EXTRA_QMAKEVARS_PRE += "WEBOS_INSTALL_PREFIX=${OE_QMAKE_PATH_PREFIX}"
 
# this value is exported in do_configure, so that project file can select MACHINE_NAME
WEBOS_QMAKE_MACHINE ?= "${MACHINE}"
# this value is defined only for make through EXTRA_OEMAKE
WEBOS_QMAKE_TARGET ?= ""

# add only when WEBOS_QMAKE_MACHINE is defined (by default it equals MACHINE)
EXPORT_WEBOS_QMAKE_MACHINE += "${@ 'export MACHINE=${WEBOS_QMAKE_MACHINE}' if d.getVar('WEBOS_QMAKE_MACHINE', True) != '' and bb.data.inherits_class('webos_machine_dep', d) and not bb.data.inherits_class('native', d) else '' }"
EXPORT_WEBOS_QMAKE_MACHINE[vardepvalue] = "${EXPORT_WEBOS_QMAKE_MACHINE}"

# add only when WEBOS_QMAKE_TARGET is defined (by default it's empty)
EXPORT_WEBOS_QMAKE_TARGET = "${@ 'MACHINE=${WEBOS_QMAKE_TARGET}' if d.getVar('WEBOS_QMAKE_TARGET', True) != '' and bb.data.inherits_class('webos_machine_dep', d) and not bb.data.inherits_class('native', d) else '' }"
EXPORT_WEBOS_QMAKE_TARGET[vardepvalue] = "${EXPORT_WEBOS_QMAKE_TARGET}"

EXTRA_OEMAKE += "${EXPORT_WEBOS_QMAKE_TARGET}"

do_configure_prepend() {
  ${EXPORT_WEBOS_QMAKE_MACHINE}
}
