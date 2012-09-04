# (c) Copyright 2012  Hewlett-Packard Development Company, L.P. 

PRINC := "${@int(PRINC) + 1}"

FILESEXTRAPATHS_prepend := "${THISDIR}/${P}:"

SRC_URI += "file://no_bash_required-webos.patch;patch=1"
