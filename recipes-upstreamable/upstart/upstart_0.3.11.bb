# Copyright (c) 2012-2013 LG Electronics, Inc.

DESCRIPTION = "Event driven system init"
SECTION = "base"
PRIORITY = "optional"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=4325afd396febcb659c36b49533135d4"

DEPENDS = ""
RRECOMMENDS_${PN} += "libupstart upstart-sysvcompat"

PR = "r12"

SRC_URI = "http://upstart.ubuntu.com/download/0.3/upstart-${PV}.tar.bz2;name=upstart \
    http://upstart.ubuntu.com/download/example-jobs/0.3/example-jobs-${PV}.tar.gz;name=compat \
    file://init/rcS-default file://init/rc file://init/rcS \
"

SRC_URI[upstart.md5sum] = "a9e475e1458c876add0441d9d4cfe9c0" 
SRC_URI[upstart.sha256sum] = "d4f7fff9112049eab318518719735d0ac66ff558ed91c2d7c7c41124de2832b6"
SRC_URI[compat.md5sum] = "22d66ef8bc9d167eb822bbfecb584107"
SRC_URI[compat.sha256sum] = "f53fae7258fcf67cb29d344e53e1548171e4781af11ff4ba92b8b81caab3c315"

inherit autotools pkgconfig update-alternatives
inherit gettext

# --enable-compat builds halt, reboot, shutdown tools
EXTRA_OECONF += "--enable-compat"

# libupstart can be used for upstart event generation from other programs.
# However it is not used by upstart itself, so package it separately.
PACKAGES =+ "libupstart libupstart-dev"
FILES_libupstart += "${libdir}/libupstart.so.*"
FILES_libupstart-dev += "${libdir}/libupstart.la ${libdir}/libupstart.so ${includedir}/upstart/"

# upstart-sysvcompat provides Sys V Init compatible tools: halt, reboot,
# shutdown, telinit. These might be needed by other scripts.
PACKAGES =+ "upstart-sysvcompat upstart-sysvcompat-doc"
FILES_upstart-sysvcompat += " \
    ${base_sbindir}/reboot.* ${base_sbindir}/halt.* ${base_sbindir}/poweroff.* \
    ${base_sbindir}/shutdown.* ${base_sbindir}/telinit ${base_sbindir}/runlevel \
    ${webos_upstartconfdir}/control-alt-delete \
    ${webos_upstartconfdir}/rc* \
    ${webos_upstartconfdir}/sulogin \
    ${sysconfdir}/init.d \
    ${sysconfdir}/default/rcS \
"
FILES_upstart-sysvcompat-doc += " \
    ${mandir}/*/reboot.* ${mandir}/*/halt.* ${mandir}/*/poweroff.* \
    ${mandir}/*/shutdown.* ${mandir}/*/telinit.* ${mandir}/*/runlevel.* \
"

RRECOMMENDS_${PN} += "${BPN}-getty"

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
    install -d ${D}${webos_upstartconfdir}

    install -m 0644 ${WORKDIR}/init/rcS-default   ${D}${sysconfdir}/default/rcS
    install -m 0755 ${WORKDIR}/init/rc            ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/init/rcS           ${D}${sysconfdir}/init.d
    install -m 0644 ${WORKDIR}/rc*                ${D}${webos_upstartconfdir}
    install -m 0644 ${WORKDIR}/control-alt-delete ${D}${webos_upstartconfdir}
    install -m 0644 ${WORKDIR}/sulogin            ${D}${webos_upstartconfdir}
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

