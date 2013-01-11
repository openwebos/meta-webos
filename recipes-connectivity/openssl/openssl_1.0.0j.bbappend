PRINC := "${@int(PRINC) + 2}"

inherit update-alternatives
ALTERNATIVE_${PN} = "openssl-cnf"
ALTERNATIVE_LINK_NAME[openssl-cnf] = "${libdir}/ssl/openssl.cnf"
ALTERNATIVE_PRIORITY[openssl-cnf] ?= "1"

# Perl is needed for the c_rehash script
RDEPENDS_${PN} += "perl"

do_install_append() {
    # The c_rehash utility isn't installed by the normal installation process and instead
    # of modifying the base recipe we're installing it here for our own purpose.
    install -m 0755 ${S}/tools/c_rehash ${D}${bindir}
}

