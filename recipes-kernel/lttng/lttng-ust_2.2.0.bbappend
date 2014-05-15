# Copyright (c) 2014 LG Electronics, Inc.

# Overriding recipe in meta-webos-backports to correct license
LICENSE = "LGPLv2.1+ & MIT & GPLv2"

PKGV .= "-0webos1"
EXTENDPRAUTO_append = "webos2"

FILESEXTRAPATHS_prepend := "${THISDIR}/${BP}:"

# Temporary patch for BHV-2914
SRC_URI += "file://0001-Workaround-gcc-cross-issue-in-lttng-ust.patch \
           "
