# Copyright (c) 2014 LG Electronics, Inc.

# Drop this .bbappend when upgrading to 1.7.4 or newer version of dbus
# Path: http://cgit.freedesktop.org/dbus/dbus/commit/?id=954d75b2b64e4799f360d2a6bf9cff6d9fee37e7

PKGV .= "-0webos1"
EXTENDPRAUTO_append = "webos1"

FILESEXTRAPATHS_prepend := "${THISDIR}/${BP}:"

SRC_URI += "file://CVE-2013-2168.patch"
