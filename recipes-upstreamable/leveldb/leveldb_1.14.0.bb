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

PR = "r4"

SRC_URI = "http://${BPN}.googlecode.com/files/${BP}.tar.gz \
    file://explicitly.disable.tcmalloc.patch \
    file://Switch-to-posix_fallocate-system-call.patch \
"
SRC_URI[md5sum] = "38ce005460d71040f959d71fd8d7fc78"
SRC_URI[sha256sum] = "9122d2c248ba40d6ce46d0c3e4738fcfa941d0d93fdba20a101471a98e8b00a3"

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
