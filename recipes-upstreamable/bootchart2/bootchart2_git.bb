# Copyright (c) 2013 LG Electronics, Inc.

SUMMARY = "Booting sequence and CPU,I/O usage monitor"
DESCRIPTION = "Monitors where the system spends its time at start, creating a graph of all processes, disk utilization, and wait time."
AUTHOR = "Wonhong Kwon <wonhong.kwon@lge.com>"
HOMEPAGE = "https://github.com/mmeeks/bootchart"
LICENSE = "GPL-3.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=44ac4678311254db62edf8fd39cb8124"

RDEPENDS_${PN} = "python-pycairo python-compression python-image"

inherit autotools
inherit update-alternatives

RCONFLICTS_${PN} = "bootchart"
ALTERNATIVE_${PN} = "init"
ALTERNATIVE_TARGET[init] = "${base_sbindir}/bootchartd"
ALTERNATIVE_LINK_NAME[init] = "${base_sbindir}/init"
ALTERNATIVE_PRIORITY = "40"

SRCREV = "4ad582906fe8a555a398829c3fd9ecfbe0d3f0c2"
PV = "0.14.5+git${SRCPV}"

SRC_URI = "git://github.com/mmeeks/bootchart.git"
SRC_URI += "file://fix-wrong-ppid-tracking-bug.patch"
SRC_URI += "file://update-cmds-of-initctls-to-upstart-event-name.patch"

S = "${WORKDIR}/git"

FILES_${PN} += "${base_libdir}/bootchart/bootchart-collector"
FILES_${PN} += "${base_libdir}/bootchart/tmpfs"
FILES_${PN} += "${libdir}"
FILES_${PN}-dbg += "${base_libdir}/bootchart/.debug"
