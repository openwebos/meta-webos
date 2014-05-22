# Copyright (c) 2014 LG Electronics, Inc.

EXTENDPRAUTO_append = "webos1"

PACKAGES =+ "${PN}-gpl"

LICENSE_${PN}-gpl = "GPLv2"

RDEPENDS_${PN} += "${PN}-gpl"

FILES_${PN}-gpl = "${base_sbindir}/vipw.shadow ${base_sbindir}/vigr.shadow ${bindir}/su"
