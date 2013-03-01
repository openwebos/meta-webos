# Copyright (c) 2012-2013 Hewlett-Packard Development Company, L.P.

PR_append = "webos2"

SRC_URI += "file://removedoc-webos.patch"
FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"
