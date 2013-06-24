# Copyright (c) 2013 LG Electronics, Inc.

PR_append = "webos2"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append_qemux86 = " file://my_gfx.cfg \
                           file://sound.cfg \
                         "

# replace meta/cfg/kernel-cache/cfg/sound.cfg with new sound.cfg 
do_patch_append_qemux86() {
    cp -v -f ${S}/../sound.cfg ${S}/meta/cfg/kernel-cache/cfg/sound.cfg
}

