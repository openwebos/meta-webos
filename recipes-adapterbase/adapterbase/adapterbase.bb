# (c) Copyright 2012  Hewlett-Packard Development Company, L.P.

DESCRIPTION = "adapterbase"
LICENSE = "Apache-2.0"
SECTION = "Linux/System"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS = "glib-2.0 npapi-headers"
RDEPENDS = ""

inherit autotools 

#PR = "${SUBMISSION_${PN}}"
SRCREV = "${AUTOREV}"
SRC_URI = "git://github-mirror.palm.com/isis-project/AdapterBase;protocol=git"
S = "${WORKDIR}/git"

ALLOW_EMPTY_${PN} = "1"

do_configure() {
	# remove these from staging to ensure we are picking up
	# the correct versions during compilation.
	rm -f ${STAGING_INCDIR}/AdapterBase.h
	rm -f ${STAGING_LIBDIR}/AdapterBase.a
}

do_compile() {
	LUNA_STAGING="." make
}

do_install() {
	install -m 444 AdapterBase.h ${STAGING_INCDIR}
	install -m 444 AdapterBase.a ${STAGING_LIBDIR}
}
do_makeclean() {
	rm -f ${STAGING_INCDIR}/AdapterBase.h
	rm -f ${STAGING_LIBDIR}/AdapterBase.a
}

