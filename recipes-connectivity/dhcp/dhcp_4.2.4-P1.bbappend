# (c) Copyright 2012  Hewlett-Packard Development Company, L.P. 

PR_append = "webos2"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://no_bash_required-webos.patch \
            file://dhclient.upstart"

# Add dhclient upstart script
# (script should be configured to only run dhclient when needed)

do_install_append () {
        install -d ${D}${sysconfdir}/event.d
        install -m 0644 ${WORKDIR}/dhclient.upstart ${D}${sysconfdir}/event.d/dhclient
}

FILES_dhcp-client += "${sysconfdir}/event.d/dhclient"

