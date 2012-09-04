# (c) Copyright 2012  Hewlett-Packard Development Company, L.P. 

PR_append = "webos1"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += " file://add_tzset-webos.patch \
             file://add_option_dash_S-webos.patch \
             file://add_option_dash_U-webos.patch \
             file://check_for_null_before_passing_to_fputs-webos.patch \
             file://defconfig \
"
