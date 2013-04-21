# Copyright (c) 2013 Hewlett-Packard Development Company, L.P.
# Copyright (c) 2013 LG Electronics, Inc.

PR_append = "webos5"

inherit update-alternatives
ALTERNATIVE_${PN} = "openssl-cnf"
ALTERNATIVE_LINK_NAME[openssl-cnf] = "${libdir}/ssl/openssl.cnf"
ALTERNATIVE_PRIORITY[openssl-cnf] ?= "1"

do_install_append() {
    # u-a renames it too late in do_package,
    # we don't want sstate reporting conflict when populating sysroot
    mv ${D}${libdir}/ssl/openssl.cnf ${D}${libdir}/ssl/openssl.cnf.${BPN}
}

