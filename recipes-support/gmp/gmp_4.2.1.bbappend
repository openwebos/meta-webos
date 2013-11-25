# Copyright (c) 2013 LG Electronics, Inc.

EXTENDPRAUTO_append = "webos1"

FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"

SRC_URI += "file://gmp-fix-ansi-c99-webos.patch"
