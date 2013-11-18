# Copyright (c) 2012-2013 LG Electronics, Inc.

EXTENDPRAUTO_append = "webos2"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += " file://add_tzset-webos.patch \
             file://add_option_dash_S-webos.patch \
             file://add_option_dash_U-webos.patch \
             file://check_for_null_before_passing_to_fputs-webos.patch \
             file://defconfig \
"
