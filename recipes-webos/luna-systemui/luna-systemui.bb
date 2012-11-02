# (c) Copyright 2012  Hewlett-Packard Development Company, L.P.

SUMMARY = "WebOS user interface support module"
SECTION = "webos/base"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

PR = "r7"

inherit webos_enhanced_submissions
inherit allarch
inherit webos_machine_dep
inherit webos_public_repo

WEBOS_GIT_TAG = "submissions/${WEBOS_SUBMISSION}"
SRC_URI = "${OPENWEBOS_GIT_REPO}/${PN};tag=${WEBOS_GIT_TAG};protocol=git"
S = "${WORKDIR}/git"

do_install() {
        #COPY ENTIRE APP
        install -d ${D}/usr/lib/luna/system/luna-systemui
        cp -vrf ${S}/* ${D}/usr/lib/luna/system/luna-systemui
        if [ -e ${S}/images/wallpaper.tar ]; then
            install -d ${D}/usr/lib/luna/system/luna-systemui/images
            tar xvf ${S}/images/wallpaper.tar --directory=${D}/usr/lib/luna/system/luna-systemui/images
        fi
}

FILES_${PN} += "${prefix}/lib/luna/system"
