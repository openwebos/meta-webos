# Copyright (c) 2012-2014 LG Electronics, Inc.

EXTENDPRAUTO_append = "webos2"
FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"

SRC_URI += "file://e2fsck.conf"

do_install_append() {
    install -d ${D}${sysconfdir}
    install -m 0644 ${WORKDIR}/e2fsck.conf ${D}/${sysconfdir}/e2fsck.conf
}
