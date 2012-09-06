# (c) Copyright 2012  Hewlett-Packard Development Company, L.P. 

PR = "webos1"

EXTRA_OECONF =+ " --disable-static"

do_configure_prepend() {
        sed -i '/^m4datadir/s,$,/${PN},' src/Makefile.am
}

do_configure_append() {
        sed -i '/^SUBDIRS/s/ doc tests//' Makefile
}

