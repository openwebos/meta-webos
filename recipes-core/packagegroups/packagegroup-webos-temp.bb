# (c) Copyright 2012  Hewlett-Packard Development Company, L.P. 

DESCRIPTION = "Temporary additions to Open webOS images"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

PR = "r2"

PACKAGES = "\
    ${PN} \
    ${PN}-dbg \
    ${PN}-dev \
    "

PACKAGE_ARCH = "${MACHINE_ARCH}"

ALLOW_EMPTY = "1"

# to replace task-webos-temp by packagegroup-webos-temp during upgrade on target
RPROVIDES_${PN} = "task-webos-temp"
RREPLACES_${PN} = "task-webos-temp"
RCONFLICTS_${PN} = "task-webos-temp"

RDEPENDS_${PN} = " \
"
