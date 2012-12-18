DESCRIPTION = "Yet Another JSON Library - A Portable JSON parsing and serialization library in ANSI C"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=da2e9aa80962d54e7c726f232a2bd1e8"

inherit cmake

SRC_URI = "git://github.com/lloyd/${PN};tag=${PV};protocol=git"

S = "${WORKDIR}/git"

