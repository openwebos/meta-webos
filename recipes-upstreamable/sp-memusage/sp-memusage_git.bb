# Copyright (c) 2013 LG Electronics, Inc.

SUMMARY = "A collection of memory usage monitoring tools and scripts"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

DEPENDS = "sp-measure"
RDEPENDS_${PN} = "sp-measure"

SRCREV = "65e6a729ce04cd90206689ae28fbabc4baf801f6"
PR = "r0"
PV = "1.3.2+git${SRCPV}"

inherit autotools

SRC_URI = "git://gitorious.org/maemo-tools/sp-memusage.git \
           file://build-fixes.patch"
S = "${WORKDIR}/git"
