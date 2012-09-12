# (c) Copyright 2012  Hewlett-Packard Development Company, L.P.

DESCRIPTION = "A base class for browser plugins loaded by webOS."
LICENSE = "Apache-2.0"
SECTION = "Linux/System"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS = "glib-2.0 npapi-headers"
PR = "r2"

inherit autotools
inherit webos_public_repo
inherit webos_submissions

WEBOS_GIT_TAG = "${WEBOS_SUBMISSION}"
SRC_URI = "${ISIS_PROJECT_GIT_REPO}/AdapterBase;tag=${WEBOS_GIT_TAG};protocol=git"
S = "${WORKDIR}/git"

ALLOW_EMPTY_${PN} = "1"

do_configure() {
    # remove these from staging to ensure we are picking up
    # the correct versions during compilation.
    rm -f ${D}${includedir}/AdapterBase.h
    rm -f ${D}${libdir}/AdapterBase.a
}

do_compile() {
    LUNA_STAGING="." make
}

do_install() {
    install -d ${D}${includedir}
    install -m 444 AdapterBase.h ${D}${includedir}
    install -d ${D}${libdir}
    install -m 444 AdapterBase.a ${D}${libdir}
}

