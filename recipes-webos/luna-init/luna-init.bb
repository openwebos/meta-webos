# (c) Copyright 2012  Hewlett-Packard Development Company, L.P. 

DESCRIPTION = "Luna Init"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"
SECTION = "webos/base"

PR = "r4"

inherit webos_component
inherit webos_public_repo
inherit webos_submissions
inherit webos_cmake

WEBOS_GIT_TAG = "submissions/${WEBOS_SUBMISSION}"
SRC_URI = "${OPENWEBOS_GIT_REPO}/${PN};tag=${WEBOS_GIT_TAG};protocol=git"
S = "${WORKDIR}/git"

do_install_append() {
    # Expand fonts tarball
    if [ -e ${S}/files/conf/fonts/fonts.tgz ]; then
        install -d ${D}/usr/share/fonts
        tar xvzf ${S}/files/conf/fonts/fonts.tgz --directory=${D}/usr/share/fonts
    fi
}

FILES_${PN} += "/usr/palm/ /etc/palm/ /usr/share/fonts/"

