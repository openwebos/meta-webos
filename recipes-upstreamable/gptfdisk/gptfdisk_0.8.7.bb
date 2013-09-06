# Copyright (c) 2013 LG Electronics, Inc.

SUMMARY = "Programs for creating and maintenance of GUID Partition Table"
SECTION = "console/utils"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"
DEPENDS = "util-linux ncurses popt icu"

PR = "r0"

SRC_URI = "${SOURCEFORGE_MIRROR}/project/${PN}/${PN}/${PV}/${BP}.tar.gz \
           file://gptfdisk-0.8.7-convenience-1.patch"

SRC_URI[md5sum] = "005b45c0b37c37a99024704fdb2ee610"
SRC_URI[sha256sum] = "39e61d9f3701e95db1bcb83ce8fb211b22f33548e3c75b17f22067c6968e91e3"

EXTRA_OEMAKE += "POPT=yes"

do_install () {
    oe_runmake install DESTDIR=${D}
}

PACKAGES =+ "${PN}-gdisk ${PN}-cgdisk ${PN}-fixparts ${PN}-sgdisk"
FILES_${PN}-gdisk += "${base_sbindir}/gdisk"
FILES_${PN}-cgdisk += "${base_sbindir}/cgdisk"
FILES_${PN}-sgdisk += "${base_sbindir}/sgdisk"
FILES_${PN}-fixparts += "${base_sbindir}/fixparts"
