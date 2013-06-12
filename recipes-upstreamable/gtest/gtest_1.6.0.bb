# Copyright (c) 2013 LG Electronics, Inc.

DESCRIPTION = "Google C++ Testing Framework"
HOMEPAGE = "https://code.google.com/p/googletest"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING;md5=cbbd27594afd089daa160d3a16dd515a"

SRC_URI = "https://googletest.googlecode.com/files/${BP}.zip"
SRC_URI[md5sum] = "4577b49f2973c90bf9ba69aa8166b786"
SRC_URI[sha256sum] = "5ec97df8e75b4ee796604e74716d1b50582beba22c5502edd055a7e67a3965d8"

PR = "r0"

# GTest developers recommend to use source code instead of linking
# against a prebuilt library.
do_install() {
    _incdir=${includedir}/gtest
    _srcdir=${prefix}/src/gtest

    install -d ${D}${_incdir}/internal
    install -d ${D}${_srcdir}/src
    install -d ${D}${_srcdir}/cmake

    install -v -m 0644 ${S}/include/gtest/*.h ${D}${_incdir}
    install -v -m 0644 ${S}/include/gtest/internal/*.h ${D}${_incdir}/internal
    install -v -m 0644 ${S}/fused-src/gtest/* ${D}${_srcdir}/src
    install -v -m 0644 ${S}/CMakeLists.txt ${D}${_srcdir}
    install -v -m 0644 ${S}/cmake/* ${D}${_srcdir}/cmake
}

sysroot_stage_all_append() {
    sysroot_stage_dir ${D}${prefix}/src ${SYSROOT_DESTDIR}${prefix}/src
}

FILES_${PN}-dev += "${prefix}/src"
