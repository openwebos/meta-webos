# Copyright (c) 2014 LG Electronics, Inc.

# Drop this .bbappend when upgrading to newer version, which will
# include this patches:
# http://patch-tracker.debian.org/patch/series/view/lighttpd/1.4.28-2+squeeze1.5/cve-2013-4508.patch
# http://patch-tracker.debian.org/patch/series/view/lighttpd/1.4.28-2+squeeze1.5/cve-2013-4559.patch
# http://patch-tracker.debian.org/patch/series/view/lighttpd/1.4.28-2+squeeze1.5/cve-2013-4560.patch

PKGV .= "-0webos1"
EXTENDPRAUTO_append = "webos1"

FILESEXTRAPATHS_prepend := "${THISDIR}/${BP}:"

SRC_URI += "file://lighttpd-cve-2013-4508.patch"
SRC_URI += "file://lighttpd-cve-2013-4559.patch"
SRC_URI += "file://lighttpd-cve-2013-4560.patch"

