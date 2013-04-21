# Copyright (c) 2012 Hewlett-Packard Development Company, L.P.
# Copyright (c) 2013 LG Electronics, Inc.

DESCRIPTION = "Open webOS image with devel components"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

IMAGE_FEATURES += "${WEBOS_IMAGE_DEFAULT_FEATURES} tools-debug tools-profile debug-tweaks webos-test"

inherit webos-image
