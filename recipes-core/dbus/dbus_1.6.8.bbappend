# Copyright (c) 2014 LG Electronics, Inc.

# Remove CVE-2013-2168.patch from this .bbappend when upgrading to 1.7.4 or newer version of dbus
# Path: http://cgit.freedesktop.org/dbus/dbus/commit/?id=954d75b2b64e4799f360d2a6bf9cff6d9fee37e7

PKGV .= "-0webos1"
EXTENDPRAUTO_append = "webos2"

FILESEXTRAPATHS_prepend := "${THISDIR}/${BP}:"

SRC_URI += "file://CVE-2013-2168.patch"

PACKAGES =+ "${PN}-gpl"
LICENSE_${PN}-gpl = "GPL-2.0"

# using "dbus" instead of "${PN}" due to:
# WARNING: Variable key RDEPENDS_${PN} ( ${PN}-gpl) replaces original key RDEPENDS_dbus
# (${@base_contains('DISTRO_FEATURES', 'ptest', 'dbus-ptest-ptest', '', d)}).
RDEPENDS_dbus += "${PN}-gpl"

FILES_${PN}-gpl = " \
    ${bindir}/dbus-cleanup-sockets \
    ${bindir}/dbus-daemon \
    ${bindir}/dbus-monitor \
    ${bindir}/dbus-send \
    ${bindir}/dbus-uuidgen \
"
