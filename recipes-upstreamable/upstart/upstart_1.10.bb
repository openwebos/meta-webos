# Copyright (c) 2013 LG Electronics, Inc.

DESCRIPTION = "Event driven system init"
SECTION = "base"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

inherit autotools pkgconfig update-alternatives
inherit gettext

DEPENDS = "cjson dbus libnih udev"

# initctl-set-env.patch extends the initctl set-env command => mark its package version
# to indicate that it's a webOS edition
PKGV .= "-0webos1"
PR = "r0"

SRC_URI[md5sum] = "1401e7fe811bb76ebbbde8a117026b80"
SRC_URI[sha256sum] = "a217d3cc31ff0708e568b1cb5d53144ad8bf00b3885dcd37c00ad5a17dbf1c46"

SRC_URI = "http://upstart.ubuntu.com/download/${PV}/upstart-${PV}.tar.gz"

SRC_URI += "file://fix-initctl-set-env.patch"
SRC_URI += "file://initctl-set-env.patch"
SRC_URI += "file://init-no-log.patch"
SRC_URI += "file://no-icons.patch"
SRC_URI += "file://remove-legacy-jobs.patch"
SRC_URI += "file://use-our-cjson.patch"

inherit autotools pkgconfig update-alternatives
inherit gettext

# libupstart can be used for upstart event generation from other programs.
# However it is not used by upstart itself, so package it separately.
PACKAGES =+ "libupstart libupstart-dev libupstart-staticdev ${PN}-systemd ${PN}-udev-bridge"

# dynamic libupstart libs for the target device
FILES_libupstart += "${libdir}/libupstart.so.*"

# add these two to libupstart above to build for libupstart
FILES_libupstart-dev += "${libdir}/libupstart.la ${libdir}/libupstart.so ${includedir}/upstart/ ${libdir}/pkgconfig/*"
FILES_libupstart-staticdev += "${libdir}/libupstart.a"

# this moves upstart-*-bridge.conf and binaries from /init, /sbin and /usr/share/upstart/* to -udev-bridge, and system.d files to -systemd ipks
FILES_${PN}-systemd = "${sysconfdir}/dbus-1/*"
FILES_${PN}-udev-bridge = "${sysconfdir}/init/* ${base_sbindir}/*bridge ${datadir}/upstart/*"

# this moves these files to the -dev ipk since they may be of use to developers
FILES_${PN}-dev += "${base_bindir}/init-checkconf ${base_bindir}/initctl2dot ${base_bindir}/upstart-monitor"
FILES_${PN}-dev += "${webos_install_datadir}/applications/upstart-monitor"

EXTRA_OEMAKE += "'bindir=${base_bindir}' \
                 'sbindir=${base_sbindir}' \
                 'usrbindir=${bindir}' \
                 'usrsbindir=${sbindir}' \
                 'includedir=${includedir}' \
                 'mandir=${mandir}'"

do_install () {
    oe_runmake 'DESTDIR=${D}' install
}
ALTERNATIVE_${PN}  = "init reboot halt poweroff shutdown telinit"

ALTERNATIVE_LINK_NAME[init] = "${base_sbindir}/init"
ALTERNATIVE_LINK_NAME[reboot] = "${base_sbindir}/reboot"
ALTERNATIVE_LINK_NAME[halt] = "${base_sbindir}/halt"
ALTERNATIVE_LINK_NAME[poweroff] = "${base_sbindir}/poweroff"
ALTERNATIVE_LINK_NAME[shutdown] = "${base_sbindir}/shutdown"
ALTERNATIVE_LINK_NAME[telinit] = "${base_sbindir}/telinit"

ALTERNATIVE_PRIORITY[init] = "60"
ALTERNATIVE_PRIORITY[reboot] = "210"
ALTERNATIVE_PRIORITY[halt] = "210"
ALTERNATIVE_PRIORITY[poweroff] = "210"
ALTERNATIVE_PRIORITY[shutdown] = "210"
ALTERNATIVE_PRIORITY[telinit] = "210"
