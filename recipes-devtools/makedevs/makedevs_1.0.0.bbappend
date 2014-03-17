# Copyright (c) 2013-2014 LG Electronics, Inc.

FILESEXTRAPATHS_prepend := "${THISDIR}/${BP}:"

PKGV .= "-0webos1"

# Drop this .bbappend when upgrading to oe-core 1.6 or newer, which will
# include this patch:
# http://git.openembedded.org/openembedded-core/commit?id=ce460dd1b9c137b00d1142801b133c083ce55e74

# currently the only difference is -0webos1 suffix in VERSION:
# < #define VERSION         "1.0.0-0webos1"
# ---
# > #define VERSION         "1.0.1"

# Add ${LDFLAGS}
do_compile() {
    ${CC} ${CFLAGS} ${LDFLAGS} -o ${S}/makedevs ${S}/makedevs.c
}
