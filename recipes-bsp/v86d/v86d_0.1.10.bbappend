# (c) Copyright 2012  Hewlett-Packard Development Company, L.P. 

PR_append = "webos1"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

# Override default fbsetup script with our own script which sets the screen resolution
SRC_URI += "file://fbsetup"
