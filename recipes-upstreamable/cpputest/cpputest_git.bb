# Copyright (c) 2013 LG Electronics, Inc.

SUMMARY = "CppUTest is a C/C++ based unit xUnit test framework for unit testing."
HOMEPAGE = "http://cpputest.github.io/"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=ce5d5f1fe02bcd1343ced64a06fd4177"

SRCREV = "ee14dc0646250d601a1d7f4f8f06ec142e65917b"
PV = "3.5+git${SRCPV}"

inherit cmake
inherit autotools

SRC_URI = "git://github.com/cpputest/cpputest.git \
           file://cpputest.pc \
           file://0001-Modification-between-cpputest-v3.5-and-specific-func.patch"

S = "${WORKDIR}/git"

do_install_append() {
    install -d ${D}${libdir}/pkgconfig
    install -v -m 644 ${WORKDIR}/cpputest.pc ${D}${libdir}/pkgconfig
}

