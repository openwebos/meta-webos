# (c) Copyright 2013  Hewlett-Packard Development Company, L.P.
# (c) Copyright 2013 LG Electronics

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

PR_append = "webos2"

SRC_URI += "file://dropbear.upstart"

do_install_append() {
    install -d ${D}${webos_upstartconfdir}
    install -m 0644 ${WORKDIR}/dropbear.upstart ${D}${webos_upstartconfdir}/dropbear
}

# move startup scripts in different packages
PACKAGES =+ "${PN}-sysvinit ${PN}-upstart"
FILES_${PN}-sysvinit = "${sysconfdir}/init.d"
FILES_${PN}-upstart = "${webos_upstartconfdir}"

# for update-rc.bbclass to know where to put postinst/prerm
UPDATERCPN = "${PN}-sysvinit"

# and we want only upstart in webOS images
RDEPENDS_${PN} += "${PN}-upstart"
