# (c) Copyright 2012  Hewlett-Packard Development Company, L.P. 

DESCRIPTION = "Open webOS image"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

PR = "r2"

IMAGE_FEATURES += "webos-temp ssh-server-dropbear"

inherit webos-image
