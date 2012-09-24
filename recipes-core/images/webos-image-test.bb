# (c) Copyright 2012  Hewlett-Packard Development Company, L.P.

DESCRIPTION = "Open webOS image with test components"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

# Image recipes are special; don't increment PR when changing.
PR = "r0"

IMAGE_FEATURES += "webos-core webos-extended webos-temp webos-test"

inherit webos-image
