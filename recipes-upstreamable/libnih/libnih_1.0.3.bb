# Copyright (c) 2013  LG Electronics, Inc.

DESCRIPTION = "libnih"
SECTION = "libs"

LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

DEPENDS = "dbus libnih-native"

PR = "r0"

SRC_URI = "https://launchpad.net/${BPN}/1.0/${PV}/+download/${BP}.tar.gz"

SRC_URI[md5sum] = "db7990ce55e01daffe19006524a1ccb0"
SRC_URI[sha256sum] = "897572df7565c0a90a81532671e23c63f99b4efde2eecbbf11e7857fbc61f405"

inherit autotools
inherit gettext

