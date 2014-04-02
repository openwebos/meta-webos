# Copyright (c) 2013-2014 LG Electronics, Inc.

EXTENDPRAUTO_append = "webos5"

# Assign to PE, because empty PKGE in KERNEL_IMAGE_BASE_NAME causes two hyphens.
PE = "1"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append_qemux86 = " file://my_gfx.cfg \
                           file://sound.cfg \
                           file://enable_uinput.cfg \
                           file://network_testing.cfg \
                         "

# replace meta/cfg/kernel-cache/cfg/sound.cfg with new sound.cfg 
do_patch_append_qemux86() {
    cp -v -f ${S}/../sound.cfg ${S}/meta/cfg/kernel-cache/cfg/sound.cfg
}

