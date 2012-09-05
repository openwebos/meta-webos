# (c) Copyright 2012  Hewlett-Packard Development Company, L.P.

DESCRIPTION = "NPAPI headers"
LICENSE = "MPL-1.1"
SECTION = "WebKit"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MPL-1.1;md5=1d38e87ed8d522c49f04e1efe0fab3ab"

#PR = "${SUBMISSION_${PN}}"
SRCREV = "${AUTOREV}"
SRC_URI = "git://github-mirror.palm.com/isis-project/npapi-headers;protocol=git"
S = "${WORKDIR}/git"

#PACKAGES = "${PN}"
ALLOW_EMPTY = "1"

do_compile() {
}

do_install() {
	ls

	# new location
	mkdir -p ${STAGING_INCDIR}/webkit/npapi
	cp -f  *.h ${STAGING_INCDIR}/webkit/npapi

	# legacy location
	cp -f  *.h ${STAGING_INCDIR}
}

do_makeclean() {
	# new location
	rm -fr ${STAGING_INCDIR}/webkit/npapi

	# legacy location
	rm -f ${STAGING_INCDIR}/npapi.h
	rm -f ${STAGING_INCDIR}/npruntime.h
	rm -f ${STAGING_INCDIR}/nppalmdefs.h
	rm -f ${STAGING_INCDIR}/npupp.h
}

