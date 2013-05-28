# Copyright (c) 2013 Hewlett-Packard Development Company, L.P.
# Copyright (c) 2013 LG Electronics, Inc.

# LevelDB library for db8 package
#
# At present this package only installs the LevelDB code
# itself (shared libraries, .so in the dev package),
# documentation and headers.
#

SECTION = "libs"
DESCRIPTION = "LevelDB is a fast key-value storage library that provides an ordered mapping from string keys to string values"
HOMEPAGE = "http://leveldb.googlecode.com"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=92d1b128950b11ba8495b64938fc164d"

SRC_URI = "http://${BPN}.googlecode.com/files/${BP}.tar.gz"
SRC_URI[md5sum] = "7e5d8fd6de0daf545bb523b53a9d47c6"
SRC_URI[sha256sum] = "84a4ab34671e1271d895f093932f8c8cfecb45b2e085da738671599825d12f62"

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
