# Copyright (c) 2013 LG Electronics, Inc.

require ${BPN}.inc

PR = "${INC_PR}.0"

DEPENDS += "qt4-webos"
inherit webos_qmake

EXTRA_OEMAKE += "PLATFORM=${TARGET_ARCH}"

do_configure_prepend() {
    ## fix DESKTOP condition handled by Qt5 but not by Qt4.
    sed 's/linux-g++ || linux-g++-64/linux-g++-64/g' ${S}/SmartKey.pro > ${S}/SmartKeyQt4.pro
    MACHINE=${MACHINE} ${QMAKE} SmartKeyQt4.pro
}

do_install_append() {
    install -d ${D}/${sysconfdir}/event.d
    install -v -m 0644 ${S}/smartkey.start ${D}/${sysconfdir}/event.d/smartkey
}
