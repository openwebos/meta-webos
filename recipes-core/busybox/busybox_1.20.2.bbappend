# Copyright (c) 2012-2014 LG Electronics, Inc.

PKGV .= "-0webos1"
EXTENDPRAUTO_append = "webos3"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://add_tzset-webos.patch"
SRC_URI += "file://add_option_dash_S-webos.patch"
SRC_URI += "file://add_option_dash_U-webos.patch"
SRC_URI += "file://check_for_null_before_passing_to_fputs-webos.patch"

# Chunk 3 was removed from patch for file mdev.c.
# Our version of this file completely different comparing with 1.21.0 version.
# Function mkdir_recursive() is called only once in build_alias() function and
# does not called in make_device() function.
SRC_URI += "file://busybox-CVE-2013-1813.patch"

SRC_URI += "file://defconfig"

