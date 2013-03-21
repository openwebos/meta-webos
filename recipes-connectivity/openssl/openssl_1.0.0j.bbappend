# Copyright (c) 2013 Hewlett-Packard Development Company, L.P.
# Copyright (c) 2013 LG Electronics

PR_append = "webos4"

inherit update-alternatives

do_install_append() {
    # Remove 'our' openssl.cnf to use the version to be put there by pmcertificatemgr
    rm -f ${D}${libdir}/ssl/openssl.cnf
}

