# Copyright (c) 2012-2014 LG Electronics, Inc.

EXTENDPRAUTO_append = "webos2"

PACKAGES = "fuser killall pstree \
            ${PN}-dbg ${PN}-staticdev ${PN} \
            ${PN}-dev ${PN}-locale psmisc-extras \
"

do_configure_prepend() {
    sed -i '/^SUBDIRS/s/=.*$/= src/' ${S}/Makefile.am
    sed -i '/^oldfuser_/s/^/#/' ${S}/src/Makefile.am
    sed -i '/^bin_PROGRAMS/s/ oldfuser//' ${S}/src/Makefile.am
}
