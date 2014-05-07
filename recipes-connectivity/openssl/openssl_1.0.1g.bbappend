# Copyright (c) 2013-2014 LG Electronics, Inc.

EXTENDPRAUTO_append = "webos8"

inherit update-alternatives
ALTERNATIVE_${PN} = "openssl-cnf"
ALTERNATIVE_LINK_NAME[openssl-cnf] = "${libdir}/ssl/openssl.cnf"
ALTERNATIVE_PRIORITY[openssl-cnf] ?= "1"

do_install_append() {
    # u-a renames it too late in do_package,
    # we don't want sstate reporting conflict when populating sysroot
    mv ${D}${libdir}/ssl/openssl.cnf ${D}${libdir}/ssl/openssl.cnf.${BPN}
}
do_install_append_class-target() {
    # pmcertificatemgr is trying to replace certs and private with symlinks to ${sysconfdir}/ssl
    # ln -sf ${sysconfdir}/ssl/certs ${D}${libdir}/ssl/certs
    # ln -sf ${sysconfdir}/ssl/private ${D}${libdir}/ssl/private
    # but it's failing with
    # * extract_archive: Cannot create symlink from ./usr/lib/ssl/certs to '/etc/ssl/certs': File exists.
    # * extract_archive: Cannot create symlink from ./usr/lib/ssl/private to '/etc/ssl/private': File exists.
    # because empty directories are already packaged in openssl package, remove them here
    rmdir ${D}${libdir}/ssl/certs
    rmdir ${D}${libdir}/ssl/private
}
