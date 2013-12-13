# Copyright (c) 2013 LG Electronics, Inc.

# The patch we are applying to fontconfig moves the location of the
# source comment in fccache.c which contains license information. As a
# result we need to change the line numbers (not the signature itself)
# for src/fccache.c. The only way to do this is to overwrite
# LIC_FILES_CHKSUM from the oe-core recipe.
LIC_FILES_CHKSUM = "file://COPYING;md5=dc5b39c592e47a22dbec44855988d2a0 \
                    file://src/fcfreetype.c;endline=45;md5=5d9513e3196a1fbfdfa94051c09dfc84 \
                    file://src/fccache.c;beginline=1187;endline=1202;md5=0326cfeb4a7333dd4dd25fbbc4b9f27f"

EXTENDPRAUTO_append = "webos1"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://Add-ignore-mtime-option-to-fc-cache.patch"
