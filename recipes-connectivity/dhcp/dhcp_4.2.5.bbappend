# Copyright (c) 2012-2013 LG Electronics, Inc.

EXTENDPRAUTO_append = "webos4"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

# [GF-43279] Override the RDEPENDS of dhcp-client on bash
# from the upstream recipe. busybox has been configured to
# provide a bash applet
RDEPENDS_dhcp-client = "busybox"

SRC_URI += "file://no_bash_required-webos.patch \
            file://dhclient.upstart"

# Add dhclient upstart script
# (script should be configured to only run dhclient when needed)

do_install_append () {
        install -d ${D}${webos_upstartconfdir}
        install -m 0644 ${WORKDIR}/dhclient.upstart ${D}${webos_upstartconfdir}/dhclient
}

FILES_dhcp-client += "${webos_upstartconfdir}/dhclient"

