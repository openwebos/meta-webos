FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
PRINC := "${@int(PRINC) + 1}"

# NOTE: we have to rewrite the SRC_URI here as we don't want the
# 0001-update-ca-certificates-remove-c-rehash.patch patch
SRC_URI = "${DEBIAN_MIRROR}/main/c/ca-certificates/ca-certificates_${PV}.tar.gz \
           file://certstoreinit"

do_install_append() {
    install -d ${D}${webos_upstartconfdir}
    install -m 0644 ${WORKDIR}/certstoreinit ${D}${webos_upstartconfdir}/certstoreinit
}

FILES_${PN} += " ${webos_upstartconfdir}/certstoreinit"

pkg_postrm_${PN}() {
    # Remove possible installed certificates by the update-ca-certificates script
    rm -rf ${sysconfdir}/ssl/certs
}
