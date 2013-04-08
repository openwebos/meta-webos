# (c) Copyright 2013 LG Electronics

DESCRIPTION = "tty configuration for upstart"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

INHIBIT_DEFAULT_DEPS = "1"

USE_VT ?= "1"
SYSVINIT_ENABLED_GETTYS ?= "1"

do_compile() {
        :
}

do_install() {
    install -d ${D}${webos_upstartconfdir}
    if [ ! -z "${SERIAL_CONSOLE}" ]; then
        cat <<EOF >> ${D}${webos_upstartconfdir}/tty-serial
# tty-serial - getty
#
# This service maintains a getty on tty-serial from the point the system is
# started until it is shut down again.

start on runlevel 2
start on runlevel 3
start on runlevel 4
start on runlevel 5

stop on runlevel 0
stop on runlevel 1
stop on runlevel 6

respawn
exec ${base_sbindir}/getty ${SERIAL_CONSOLE}
EOF
    fi
    if [ "${USE_VT}" = "1" ]; then
        for n in ${SYSVINIT_ENABLED_GETTYS}
        do
            cat <<EOF >> ${D}${webos_upstartconfdir}/tty$n
# tty$n - getty
#
# This service maintains a getty on tty$n from the point the system is
# started until it is shut down again.

start on runlevel 2
start on runlevel 3
start on runlevel 4
start on runlevel 5

stop on runlevel 0
stop on runlevel 1
stop on runlevel 6

respawn
exec ${base_sbindir}/getty 38400 tty$n
EOF
        done
    fi
}

FILES_${PN} = "${webos_upstartconfdir}"
OLD_UPSTART = "0.3.11-r10.0"
RCONFLICTS_${PN} = "upstart (< ${OLD_UPSTART})"
RREPLACES_${PN} = "upstart (< ${OLD_UPSTART})"
RDEPENDS_${PN} = "upstart"

# SERIAL_CONSOLE, USE_VT and SYSVINIT_ENABLED_GETTYS are generally defined by the MACHINE .conf.
# Set PACKAGE_ARCH appropriately.
PACKAGE_ARCH = "${MACHINE_ARCH}"
