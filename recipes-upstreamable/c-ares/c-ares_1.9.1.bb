# Copyright (c) 2012-2013 LG Electronics, Inc.

DESCRIPTION = "c-ares is a C library that resolves names asynchronously."
HOMEPAGE = "http://daniel.haxx.se/projects/c-ares/"
SECTION = "libs"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit autotools
inherit pkgconfig

SRC_URI = "http://c-ares.haxx.se/download/c-ares-${PV}.tar.gz"

S = "${WORKDIR}/c-ares-${PV}"
PR = "r1"

EXTRA_OECONF = "--enable-shared"

do_install_append() {
    install -d ${D}/${includedir}/ares
    install -m 0644 ares*.h ${D}/${includedir}/ares/
}

FILES_${PN}-dev += "${includedir}/ares/*.h"

SRC_URI[md5sum] = "389db4917a3b58c4ce4ebfe961fd84c4"
SRC_URI[sha256sum] = "023f28001f2f839645c8700187391a011198950c73ddd91510c7549d87373936"

