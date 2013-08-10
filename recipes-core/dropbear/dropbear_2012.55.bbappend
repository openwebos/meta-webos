# Copyright (c) 2013 LG Electronics, Inc.

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

EXTENDPRAUTO_append = "webos4"

SRC_URI += "file://dropbear.upstart"

do_install_append() {
    install -d ${D}${webos_upstartconfdir}
    install -m 0644 ${WORKDIR}/dropbear.upstart ${D}${webos_upstartconfdir}/dropbear
    sed -i -e 's:WEBOSPERSISTENTSTORAGEDIR:${webos_persistentstoragedir}:g' ${D}${webos_upstartconfdir}/dropbear
}

# move startup scripts in different packages
PACKAGES =+ "${PN}-sysvinit ${PN}-upstart"
FILES_${PN}-sysvinit = "${sysconfdir}/init.d"
FILES_${PN}-upstart = "${webos_upstartconfdir}"

# for update-rc.bbclass to know where to put postinst/prerm
UPDATERCPN = "${PN}-sysvinit"

# and we want only upstart in webOS images
RDEPENDS_${PN} += "${PN}-upstart"
