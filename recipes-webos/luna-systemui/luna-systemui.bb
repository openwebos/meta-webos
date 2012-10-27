# (c) Copyright 2012  Hewlett-Packard Development Company, L.P.

DESCRIPTION = "WebOS user interface support module"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"
SECTION = "webos/base"

INHIBIT_DEFAULT_DEPS = "1"

PR = "r6"

inherit webos_enhanced_submissions
inherit allarch
inherit webos_machine_dep

WEBOS_GIT_TAG = "submissions/${WEBOS_SUBMISSION}"
SRC_URI = "${OPENWEBOS_GIT_REPO}/${PN};tag=${WEBOS_GIT_TAG};protocol=git"
S = "${WORKDIR}/git"

do_install() {
        #COPY ENTIRE APP
        install -d ${D}/usr/lib/luna/system/luna-systemui
        cp -rf ${S}/* ${D}/usr/lib/luna/system/luna-systemui
        if [ -e ${S}/images/wallpaper.tar ]; then
            install -d ${D}/usr/lib/luna/system/luna-systemui/images
            tar xf ${S}/images/wallpaper.tar --directory=${D}/usr/lib/luna/system/luna-systemui/images
        fi
}

FILES_${PN} += "${prefix}/lib/luna/system"
