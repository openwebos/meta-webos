# (c) Copyright 2012  Hewlett-Packard Development Company, L.P. 

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

do_configure_append() {
    sed -i s:#define\ HAVE_CLOCK_GETTIME_MONOTONIC\ 1:/*\ #undef\ HAVE_CLOCK_GETTIME_MONOTONIC\ */:g ares_config.h
}

FILES_${PN}-dev += "${includedir}/ares/*.h"

SRC_URI[md5sum] = "dd71e8f07d9f3c837e12a5416d1b7f73"
SRC_URI[sha256sum] = "6b8a7c4ecd67240cca50a345bc955ffbe2d6d1f43982defc77a8e76031a6cb06"

