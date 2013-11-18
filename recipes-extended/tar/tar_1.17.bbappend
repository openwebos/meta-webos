# Copyright (c) 2013 LG Electronics, Inc.

EXTENDPRAUTO_append = "webos1"

FILESEXTRAPATHS_prepend := "${THISDIR}/${P}:"

SRC_URI += " file://CVE-2007-4476.diff \
             file://CVE-2007-4131.diff \
"
