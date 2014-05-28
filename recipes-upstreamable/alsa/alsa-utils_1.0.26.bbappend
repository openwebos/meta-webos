# Copyright (c) 2014 LG Electronics, Inc.

FILESEXTRAPATHS_prepend := "${THISDIR}/${BP}:"

# this patch adds --disable-libsamplerate configure option
SRC_URI += "file://disable-libsamplerate.patch"

DEPENDS = "alsa-lib ncurses udev"

PACKAGECONFIG = ""
PACKAGECONFIG[libsamplerate] = "--enable-libsamplerate,--disable-libsamplerate,libsamplerate0"
