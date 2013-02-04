# (c) Copyright 2012  Hewlett-Packard Development Company, L.P. 

PR_append = "webos1"

SRC_URI += "file://connman"

# Replace connman init.d script

do_install_append () {
        install -m 0755 ${WORKDIR}/connman ${D}${sysconfdir}/init.d/connman
}
