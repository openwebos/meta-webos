# (c) Copyright 2013 Hewlett-Packard Development Company, L.P.

PR_append = "webos1"

# for all 3 opkg classes use only prefix (/usr) because recipe itself appends /lib to it.
target_localstatedir := "${prefix}"

do_install_append() {
    # We need to create the lock directory
    install -d ${D}${libdir}/opkg
    # ${localstatedir}/lib/opkg is created by do_install_append in original .bb
    rmdir ${D}${localstatedir}/lib/opkg
    rmdir ${D}${localstatedir}/lib
    rmdir ${D}${localstatedir}
}

FILES_libopkg = "${libdir}/*.so.* ${libdir}/opkg"
