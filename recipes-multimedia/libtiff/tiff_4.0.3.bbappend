# Copyright (c) 2014 LG Electronics, Inc.

# Drop this .bbappend when upgrading to newer version, which will
# include this patches:
# http://code.ohloh.net/file?fid=FWWIa8ILD2xvUyed2J_uxdm7fuk&cid=P9m-lyBa7_I&s=&fp=306441&mp=&projSelected=true#L0
# http://code.ohloh.net/file?fid=I7lyTfvG7SNVE8nSLab-cInPx3g&cid=P9m-lyBa7_I&s=&fp=306441&mp=&projSelected=true#L0

PKGV .= "-0webos1"
EXTENDPRAUTO_append = "webos1"

FILESEXTRAPATHS_prepend := "${THISDIR}/${BP}:"

SRC_URI += "file://libtiff-CVE-2013-4231.patch"
SRC_URI += "file://libtiff-CVE-2013-4244.patch"
