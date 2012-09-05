# (c) Copyright 2012  Hewlett-Packard Development Company, L.P. 

DESCRIPTION = "Internal components used in Open webOS"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

PR = "r2"

inherit packagegroup

PACKAGE_ARCH = "${MACHINE_ARCH}"

# to replace task-webos-internal by packagegroup-webos-internal during upgrade on target
RPROVIDES_${PN} = "task-webos-internal"
RREPLACES_${PN} = "task-webos-internal"
RCONFLICTS_${PN} = "task-webos-internal"

RDEPENDS_${PN} = " \
"
