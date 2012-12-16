# (c) Copyright 2012  Hewlett-Packard Development Company, L.P. 

PR_append = "webos1"
FILESEXTRAPATHS_prepend := "${THISDIR}"
SRC_URI += "file://e2fsck.conf"

do_install_append() {
    install -d ${D}${sysconfdir}
    install -m 0644 ${WORKDIR}/e2fsck.conf ${D}/${sysconfdir}/e2fsck.conf
}

