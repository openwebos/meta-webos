# Copyright (c) 2013-2014 LG Electronics, Inc.

require vboxguestdrivers.inc

PR = "${INC_PR}.1"

VBOX_NAME = "VirtualBox-${PV}"

SRC_URI = "http://download.virtualbox.org/virtualbox/${PV}/${VBOX_NAME}.tar.bz2 \
           file://Makefile.utils \
          "
SRC_URI[md5sum] = "422ab09b7eb9c0564b55a6a0a6e6678f"
SRC_URI[sha256sum] = "ffabd8735bdc80753cec29d01b499769f41e083f37a3f58b3055f19d3b1b9641"
