# (c) Copyright 2012  Hewlett-Packard Development Company, L.P. 

PR_append = "webos2"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

# Override default fbsetup script with our own script which sets the screen resolution
SRC_URI += "file://fbsetup"

# Define the desired resolution and write it into the fbsetup script
UVESA_MODE = "1024x768-32"

do_configure_prepend() {
    sed "s/modprobe uvesafb.*$/modprobe uvesafb mode_option=${UVESA_MODE}/" ${WORKDIR}/fbsetup
}
