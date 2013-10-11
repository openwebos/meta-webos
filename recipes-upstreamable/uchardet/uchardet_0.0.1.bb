# Copyright (c) 2013 LG Electronics, Inc.

DESCRIPTION = "uchardet is a C language binding of the original C++ \
implementation of the universal charset detection library by Mozilla."
HOMEPAGE = "https://code.google.com/p/uchardet/"
LICENSE = "MPLv1.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=bfe1f75d606912a4111c90743d6c7325"

PR = "r0"

SRC_URI = "https://uchardet.googlecode.com/files/${BP}.tar.gz"

SRC_URI[md5sum] = "9c17f0aca38c66c95d400691a9160b1b"
SRC_URI[sha256sum] = "e238c212350e07ebbe1961f8f128faaa40f71b70d37b63ffa2fe12c664269ee6"

S = "${WORKDIR}/${BP}"

inherit cmake

OECMAKE_SOURCEPATH = "${S}"
OECMAKE_BUILDPATH = "${S}/build"
