# (c) 2013 Hewlett-Packard Development Company, L.P.
# (c) 2013 LG Electronics

PRINC := "${@int(PRINC) + 3}"

inherit update-alternatives

# Perl is needed for the c_rehash script
RDEPENDS_${PN} += "perl"

do_install_append() {
    # The c_rehash utility isn't installed by the normal installation process and instead
    # of modifying the base recipe we're installing it here for our own purpose.
    install -m 0755 ${S}/tools/c_rehash ${D}${bindir}
    # Remove 'our' openssl.cnf to use the version to be put there by pmcertificatemgr
    rm -f ${D}${libdir}/ssl/openssl.cnf
}

