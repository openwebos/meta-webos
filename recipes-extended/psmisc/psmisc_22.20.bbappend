# Copyright (c) 2012 Hewlett-Packard Development Company, L.P.

PR_append = "webos1"

PACKAGES = "fuser killall pstree \
            ${PN}-dbg ${PN}-staticdev ${PN} \
            ${PN}-dev ${PN}-locale psmisc-extras \
"

do_configure_prepend() {
    sed -i '/^SUBDIRS/s/=.*$/= src/' Makefile.am
    sed -i '/^oldfuser_/s/^/#/' src/Makefile.am
    sed -i '/^bin_PROGRAMS/s/ oldfuser//' src/Makefile.am
}
