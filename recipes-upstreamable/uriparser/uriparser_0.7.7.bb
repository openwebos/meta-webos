# Copyright (c) 2012 Hewlett-Packard Development Company, L.P.

SECTION = "uriparser"
DESCRIPTION = "RFC 3986 compliant URI parsing library"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=72b0f9c74ae96eeab8cf1bf3efe08da2"

PR = "r0"

SRC_URI := "${SOURCEFORGE_MIRROR}/project/uriparser/Sources/${PV}/uriparser-${PV}.tar.bz2"

inherit autotools

EXTRA_OECONF = "--disable-test --disable-doc"

SRC_URI[md5sum] = "2da950ef006be5a842dcc383cbbeaa78"
SRC_URI[sha256sum] = "868cbcd71ffe875e13034a11d0c96243cbd533b934894fd2a03539eadf0d7800"
