# (c) Copyright 2012  Hewlett-Packard Development Company, L.P. 

SECTION = "libs"
DESCRIPTION = "mjson"
LICENSE = "GNUv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=fbc093901857fcd118f065f900982c24"

PR = "r0"

SRCREV = "HEAD"
SRC_URI = "svn://mjson.svn.sourceforge.net/svnroot/mjson/tag/;module=${PV};proto=http"
S = "${WORKDIR}/${PV}"

inherit autotools

ALLOW_EMPTY = "1"

BBCLASSEXTEND = "native"

