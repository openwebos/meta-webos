# (c) Copyright 2013  Hewlett-Packard Development Company, L.P.

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
SRC_URI = "http://leveldb.googlecode.com/files/${P}.tar.gz \
           file://0001-Surround-with-quotes-variables-like-CC.patch"

SRC_URI[md5sum] = "12f11385cb58ae0de66d4bc2cc7f8194"
SRC_URI[sha256sum] = "b2699b04e5aba8e98382c4955b94725d1f76bd0b5decd60c5628205b717a1d4f"

do_compile() {
    cd ${S}

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

