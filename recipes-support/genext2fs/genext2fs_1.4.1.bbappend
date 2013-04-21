# Copyright (c) 2012 Hewlett-Packard Development Company, L.P.

PR_append = "webos1"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += " file://fastSymlink-webos.patch "

BBCLASSEXTEND = "native"
