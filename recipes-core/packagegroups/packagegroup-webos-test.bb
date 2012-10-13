# (c) Copyright 2012  Hewlett-Packard Development Company, L.P. 

DESCRIPTION = "Test components used in Open webOS"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

PR = "r2"

inherit packagegroup

PACKAGE_ARCH = "${MACHINE_ARCH}"

ALLOW_EMPTY = "1"

RDEPENDS_${PN} = "\
     ltp \
"

# For backwards compatibility after rename
# I don't think these are needed
RPROVIDES_${PN} = "task-webos-test"
RREPLACES_${PN} = "task-webos-test"
RCONFLICTS_${PN} = "task-webos-test"
