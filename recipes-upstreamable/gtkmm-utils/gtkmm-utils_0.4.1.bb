DESCRIPTION = "C++ utility and widget library based on glibmm and gtkmm"
HOMEPAGE = "http://code.google.com/p/gtkmm-utils/"
SECTION = "libs"

LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=9740aaebee0708fe3dd67fdc3f6902b2"

DEPENDS = "glibmm"

PR = "r1"

SRC_URI = "http://${BPN}.googlecode.com/files/${BP}.tar.gz \
    file://0001-configure-add-option-to-disable-building-gtkmm-utils.patch \
    file://0002-Fix-includes-with-newer-glib.patch \
    file://0003-Fix-log-definition.patch \
"
SRC_URI[md5sum] = "8b9d5503aaba434f5811e530208962b3"
SRC_URI[sha256sum] = "bce5d908d1bec9cec0a74514458e6b9cf5198835fc6383c94d27ba388aae938d"

PACKAGECONFIG ??= "${@base_contains('DISTRO_FEATURES', 'x11', 'gtkmm', '', d)}"
PACKAGECONFIG[gtkmm] = "--enable-gtkmm,--disable-gtkmm,gtkmm"

inherit autotools pkgconfig
