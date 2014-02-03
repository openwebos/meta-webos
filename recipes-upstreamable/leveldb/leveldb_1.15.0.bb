# Copyright (c) 2013-2014 LG Electronics, Inc.

# LevelDB library for db8 package
#
# At present this package only installs the LevelDB code
# itself (shared libraries, .so in the dev package),
# documentation and headers.
#

SECTION = "libs"
DESCRIPTION = "LevelDB is a fast key-value storage library that provides an ordered mapping from string keys to string values"
HOMEPAGE = "http://leveldb.googlecode.com"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=92d1b128950b11ba8495b64938fc164d"

PR = "r6"

SRC_URI = "http://${BPN}.googlecode.com/files/${BP}.tar.gz \
    file://explicitly.disable.tcmalloc.patch \
"
SRC_URI[md5sum] = "e91fd7cbced8b84e21f357a866ad226a"
SRC_URI[sha256sum] = "d7c4ec571ef2ee9719f31db06344a1eba495e6abeeac1bc349d0d38a911c6680"

do_compile() {
    # do not use oe_runmake. oe_runmake pass to make compilation arguments and override
    # leveldb makefile variable CFLAGS and broke leveldb build.
    CFLAGS="${CFLAGS}" make || die
}

do_install() {
    install -d ${D}${libdir}
    oe_libinstall -C ${S} -so libleveldb ${D}${libdir}
    install -d ${D}${includedir}/leveldb
    install -m 644 ${S}/include/leveldb/*.h ${D}${includedir}/leveldb/
}
