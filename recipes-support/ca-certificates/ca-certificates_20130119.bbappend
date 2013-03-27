# (c) Copyright 2013 Hewlett-Packard Development Company, L.P.
# (c) Copyright 2013 LG Electronics

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
PR_append = "webos2"

CERT_SOURCE_DIR = "${datadir}/ca-certificates"
CERT_TARGET_DIR = "${sysconfdir}/ssl/certs"

inherit webos_certificates

do_install_append() {
    cd ${D}${sysconfdir}/ssl/certs
    for a in *.pem
    do
        if [ -e $a ] ; then
            cat $a >>${D}${sysconfdir}/ssl/certs/ca-certificates.crt
        fi
    done
}

