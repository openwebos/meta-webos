# (c) Copyright 2012  Hewlett-Packard Development Company, L.P. 
# (c) Copyright 2013 LG Electronics

PR_append = "webos1"

FILESEXTRAPATHS_prepend := "${THISDIR}/${P}:"

SRC_URI += "file://removedoc-webos.patch"
