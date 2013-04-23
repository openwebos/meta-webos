# Copyright (c) 2013 LG Electronics, Inc.

INHIBIT_UPDATERCD_BBCLASS = "1"

PR_append = "webos1"

FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"

SRC_URI += "file://connman.upstart"

do_install_append() {
    install -d ${D}${webos_upstartconfdir}
    install -v -m 644 ${WORKDIR}/connman.upstart ${D}${webos_upstartconfdir}/connman
}

