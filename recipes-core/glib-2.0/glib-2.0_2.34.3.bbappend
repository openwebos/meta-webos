# Copyright (c) 2013 LG Electronics, Inc.

EXTENDPRAUTO_append = "webos2"

FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"

SRC_URI += "file://fix-joiningleaving-multicast-groups.patch \
            file://0001-Add-caching-for-the-receiver-addresses-for-g_socket_.patch \
"
