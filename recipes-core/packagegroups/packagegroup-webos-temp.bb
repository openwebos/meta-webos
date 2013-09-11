# Copyright (c) 2012-2013 LG Electronics, Inc.

DESCRIPTION = "Temporary additions to Open webOS images"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

# You don't need to change this value when you're changing just RDEPENDS_${PN} variable.
PR = "r3"

inherit packagegroup

PACKAGE_ARCH = "${MACHINE_ARCH}"

# to replace task-webos-temp by packagegroup-webos-temp during upgrade on target
RPROVIDES_${PN} = "task-webos-temp"
RREPLACES_${PN} = "task-webos-temp"
RCONFLICTS_${PN} = "task-webos-temp"

RDEPENDS_${PN} = " \
"
