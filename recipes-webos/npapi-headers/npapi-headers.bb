# (c) Copyright 2012  Hewlett-Packard Development Company, L.P.

DESCRIPTION = "The header files used to develop NPAPI plugins and browsers."
LICENSE = "MPL-1.1"
SECTION = "webos/devel"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MPL-1.1;md5=1d38e87ed8d522c49f04e1efe0fab3ab"

inherit webos_public_repo
inherit webos_submissions

PR = "r1"
WEBOS_GIT_TAG = "${WEBOS_SUBMISSION}"
SRC_URI = "${ISIS_PROJECT_GIT_REPO}/${PN};tag=${WEBOS_GIT_TAG};protocol=git"
S = "${WORKDIR}/git"
ALLOW_EMPTY = "1"

do_compile() {
    :
}

do_install() {
    # new location
    install -d ${D}${includedir}/webkit/npapi
    install -m 666 *.h ${D}${includedir}/webkit/npapi

    # legacy location
    # install -m 666 *.h ${D}${includedir}
    cd ${D}${includedir}
    ln -s webkit/npapi/*.h .
}
