# Copyright (c) 2014 LG Electronics, Inc.

# Drop this .bbappend when upgrading to 1.3.1 or newer version of libjpeg-turbo
# CVE-2013-6629бб CVE-2013-6630: https://lists.fedoraproject.org/pipermail/scm-commits/Week-of-Mon-20131216/1161751.html

PKGV .= "-0webos1"
EXTENDPRAUTO_append = "webos1"

FILESEXTRAPATHS_prepend := "${THISDIR}/${BP}:"

SRC_URI += "file://libjpeg-turbo-CVE-2013-6629.patch"
SRC_URI += "file://libjpeg-turbo-CVE-2013-6630.patch"
