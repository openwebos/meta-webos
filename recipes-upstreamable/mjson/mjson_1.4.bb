# (c) Copyright 2012  Hewlett-Packard Development Company, L.P. 

SECTION = "libs"
DESCRIPTION = "mjson"
LICENSE = "LGPL-2.1"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/LGPL-2.1;md5=1a6d268fd218675ffea8be556788b780"

PR = "r0"

SRCREV = "HEAD"
SRC_URI = "svn://mjson.svn.sourceforge.net/svnroot/mjson/tag/;module=${PV};protocol=http"
S = "${WORKDIR}/${PV}"

inherit autotools

ALLOW_EMPTY = "1"

BBCLASSEXTEND = "native"

