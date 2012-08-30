# (c) Copyright 2012  Hewlett-Packard Development Company, L.P. 

PR_append = "webos1"

inherit update-rc.d

INITSCRIPT_PACKAGES = "dhcp-server"
INITSCRIPT_NAME = "dhcp-server"
INITSCRIPT_PARAMS = "start 90 5 3 2 . stop 20 0 1 6 ."

FILESEXTRAPATHS_prepend := "${THISDIR}/${P}:"
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://no_bash_required-webos.patch;patch=1"
