# Copyright (c) 2013-2014 LG Electronics, Inc.

DESCRIPTION = "Google C++ Testing Framework"
HOMEPAGE = "https://code.google.com/p/googletest"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=cbbd27594afd089daa160d3a16dd515a"

SRC_URI = "https://googletest.googlecode.com/files/${BP}.zip"
SRC_URI[md5sum] = "2d6ec8ccdf5c46b05ba54a9fd1d130d7"
SRC_URI[sha256sum] = "247ca18dd83f53deb1328be17e4b1be31514cedfc1e3424f672bf11fd7e0d60d"

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
