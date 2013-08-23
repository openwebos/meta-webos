# Copyright (c) 2012-2013 LG Electronics, Inc.

EXTENDPRAUTO_append = "webos2"

SRC_URI += "file://removedoc-webos.patch"
FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"
