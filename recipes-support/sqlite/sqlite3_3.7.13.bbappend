# (c) Copyright 2012  Hewlett-Packard Development Company, L.P. 

PR_append = "webos1"

SRC_URI += "file://removedoc-webos.patch;patch=1"
FILESEXTRAPATHS_prepend := "${THISDIR}/${P}:"
