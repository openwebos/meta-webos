# (c) Copyright 2012  Hewlett-Packard Development Company, L.P.

SUMMARY = "Provides certificate storage and wrapper API on openssl certificate manipulation functions"
SECTION = "webos/libs"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS = "openssl"

PR = "r2"

inherit webos_component
inherit webos_public_repo
inherit webos_enhanced_submissions
inherit webos_cmake
inherit webos_library
inherit webos_machine_impl_dep

WEBOS_GIT_TAG = "submissions/${WEBOS_SUBMISSION}"
SRC_URI = "${OPENWEBOS_GIT_REPO}/${PN};tag=${WEBOS_GIT_TAG};protocol=git"
S = "${WORKDIR}/git"

RCONFLICTS_${PN} = "openssl-misc"

do_install_append() {
    # We ship our own modified openssl configuration and as long as the
    # openssl-misc package is not installed within the same image we don't
    # get a conflict.
    install -d ${D}${sysconfdir}/ssl
    install -m 0644 ${S}/files/conf/ssl/openssl.cnf ${D}${sysconfdir}/ssl

    # because openssl expects it's default configuration file in libdir
    # we have to symlink it there together with some needed directories.
    install -d ${D}${libdir}/ssl
    ln -sf ${sysconfdir}/ssl/openssl.cnf ${D}${libdir}/ssl/openssl.cnf
    ln -sf ${sysconfdir}/ssl/certs ${D}${libdir}/ssl/certs
    ln -sf ${sysconfdir}/ssl/private ${D}${libdir}/ssl/private
}

FILES_${PN} += "${libdir}/ssl/"
