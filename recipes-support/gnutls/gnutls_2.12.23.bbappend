# Copyright (c) 2014 LG Electronics, Inc.

# Drop this .bbappend when upgrading to gnutls 3.0 or newer, which will
# include this patch:
# https://gitorious.org/gnutls/gnutls/commit/5164d5a1d57cd0372a5dd074382ca960ca18b27d

PKGV .= "-0webos1"
EXTENDPRAUTO_append = "webos1"

FILESEXTRAPATHS_prepend := "${THISDIR}/${BP}:"
SRC_URI += "file://CVE-2013-2116.patch"
