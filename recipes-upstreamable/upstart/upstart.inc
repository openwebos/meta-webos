SECTION = "base"
PRIORITY = "optional"
DEPENDS = ""
DESCRIPTION = "Event driven system init"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=4325afd396febcb659c36b49533135d4"

INC_PR = "r8"

inherit autotools pkgconfig update-alternatives
inherit gettext

# --enable-compat builds halt, reboot, shutdown tools
EXTRA_OECONF += "--enable-compat"

# libupstart can be used for upstart event generation from other programs.
# However it is not used by upstart itself, so package it separately.
PACKAGES =+ "libupstart libupstart-dev"
FILES_libupstart += "${libdir}/libupstart.so.*"
FILES_libupstart-dev += "${libdir}/libupstart.la ${libdir}/libupstart.so ${includedir}/upstart/"
FILES_staticdev += "${libdir}/libupstart.a ${includedir}/upstart/"

# upstart-sysvcompat provides Sys V Init compatible tools: halt, reboot,
# shutdown, telinit. These might be needed by other scripts.
PACKAGES =+ "upstart-sysvcompat upstart-sysvcompat-doc"
FILES_upstart-sysvcompat += " \
    ${base_sbindir}/reboot.* ${base_sbindir}/halt.* ${base_sbindir}/poweroff.* \
    ${base_sbindir}/shutdown.* ${base_sbindir}/telinit ${base_sbindir}/runlevel \
    ${sysconfdir}/event.d/control-alt-delete \
    ${sysconfdir}/event.d/rc* \
    ${sysconfdir}/event.d/sulogin \
    ${sysconfdir}/init.d \
    ${sysconfdir}/default/rcS \
"
FILES_upstart-sysvcompat-doc += " \
    ${mandir}/*/reboot.* ${mandir}/*/halt.* ${mandir}/*/poweroff.* \
    ${mandir}/*/shutdown.* ${mandir}/*/telinit.* ${mandir}/*/runlevel.* \
"

EXTRA_OEMAKE += "'bindir=${base_bindir}' \
                 'sbindir=${base_sbindir}' \
                 'usrbindir=${bindir}' \
                 'usrsbindir=${sbindir}' \
                 'includedir=${includedir}' \
                 'mandir=${mandir}'"

do_install () {
    oe_runmake 'DESTDIR=${D}' install
    install -d ${D}${sysconfdir}
    install -d ${D}${sysconfdir}/default
    install -d ${D}${sysconfdir}/init.d
    install -d ${D}${sysconfdir}/event.d

    install -m 0644 ${WORKDIR}/init/rcS-default   ${D}${sysconfdir}/default/rcS
    install -m 0755 ${WORKDIR}/init/rc            ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/init/rcS           ${D}${sysconfdir}/init.d
    install -m 0644 ${WORKDIR}/rc*                ${D}${sysconfdir}/event.d
    install -m 0644 ${WORKDIR}/control-alt-delete ${D}${sysconfdir}/event.d
    install -m 0644 ${WORKDIR}/sulogin            ${D}${sysconfdir}/event.d

    if [ ! -z "${SERIAL_CONSOLE}" ]; then
        cat <<EOF >> ${D}${sysconfdir}/event.d/tty-serial
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
            cat <<EOF >> ${D}${sysconfdir}/event.d/tty$n
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

ALTERNATIVE_${PN}  = "init"
ALTERNATIVE_${PN}-sysvcompat  = "reboot halt poweroff shutdown telinit"

ALTERNATIVE_LINK_NAME[init] = "${base_sbindir}/init"
ALTERNATIVE_LINK_NAME[reboot] = "${base_sbindir}/reboot"
ALTERNATIVE_LINK_NAME[halt] = "${base_sbindir}/halt"
ALTERNATIVE_LINK_NAME[poweroff] = "${base_sbindir}/poweroff"
ALTERNATIVE_LINK_NAME[shutdown] = "${base_sbindir}/shutdown"
ALTERNATIVE_LINK_NAME[telinit] = "${base_sbindir}/telinit"

ALTERNATIVE_PRIORITY[init] = "60"
ALTERNATIVE_PRIORITY[reboot] = "200"
ALTERNATIVE_PRIORITY[halt] = "200"
ALTERNATIVE_PRIORITY[poweroff] = "200"
ALTERNATIVE_PRIORITY[shutdown] = "200"
ALTERNATIVE_PRIORITY[telinit] = "200"
