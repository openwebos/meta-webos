# (c) Copyright 2013 Hewlett-Packard Development Company, L.P.
# (c) Copyright 2013 LG Electronics

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
PR_append = "webos1"

CERT_SOURCE_DIR = "${datadir}/ca-certificates"
CERT_TARGET_DIR = "${sysconfdir}/ssl/certs"

inherit webos_certificates

