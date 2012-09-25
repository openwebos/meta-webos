# (c) Copyright 2012  Hewlett-Packard Development Company, L.P.

DESCRIPTION = "This is an NPAPI browser plugin to isis-browser."
LICENSE = "Apache-2.0"
SECTION = "webos/libs"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS = "adapterbase browserserver libpng npapi-headers glib-2.0 qt4-webos"
PR="r1"

inherit autotools
inherit pkgconfig
inherit webos_public_repo
inherit webos_submissions

WEBOS_GIT_TAG = "${WEBOS_SUBMISSION}"
SRC_URI = "${ISIS-PROJECT_GIT_REPO}/BrowserAdapter;tag=${WEBOS_GIT_TAG};protocol=git"
S = "${WORKDIR}/git"

FILES_${PN} += "${libdir}/BrowserPlugins/BrowserAdapter.so ${libdir}/BrowserPlugins/BrowserAdapterData/*.png"
FILES_${PN}-dbg += "${libdir}/BrowserPlugins/.debug/BrowserAdapter.so"

do_compile_prepend() {
        export TARGET_ARCH="${TARGET_ARCH}"
        export STAGING_INC_DIR="${STAGING_INCDIR}"
        export STAGING_LIB_DIR="${STAGING_LIBDIR}"
        export QT_INSTALL_PREFIX="${STAGING_DIR}/${MACHINE}"
}

do_install_prepend() {
        export INSTALL_DIR="${D}"
}
do_install() {
    install -d ${D}${libdir}/BrowserPlugins/BrowserAdapterData
    install -m 755 -p ${S}/release-${TARGET_ARCH}/BrowserAdapter.so ${D}${libdir}/BrowserPlugins

    install -m 644 -p ${S}/data/launcher-bookmark-alpha.png ${D}${libdir}/BrowserPlugins/BrowserAdapterData
    install -m 644 -p ${S}/data/launcher-bookmark-overlay.png ${D}${libdir}/BrowserPlugins/BrowserAdapterData
    install -m 644 -p ${S}/data/shift-tap-reticle.png ${D}${libdir}/BrowserPlugins/BrowserAdapterData

    install -m 644 -p ${S}/data/fl-win-shdw-bot.png ${D}${libdir}/BrowserPlugins/BrowserAdapterData
    install -m 644 -p ${S}/data/fl-win-shdw-bot-left-corner.png ${D}${libdir}/BrowserPlugins/BrowserAdapterData
    install -m 644 -p ${S}/data/fl-win-shdw-bot-right-corner.png ${D}${libdir}/BrowserPlugins/BrowserAdapterData
    install -m 644 -p ${S}/data/fl-win-shdw-left.png ${D}${libdir}/BrowserPlugins/BrowserAdapterData
    install -m 644 -p ${S}/data/fl-win-shdw-right.png ${D}${libdir}/BrowserPlugins/BrowserAdapterData
    install -m 644 -p ${S}/data/fl-win-shdw-top.png ${D}${libdir}/BrowserPlugins/BrowserAdapterData
    install -m 644 -p ${S}/data/fl-win-shdw-top-left-corner.png ${D}${libdir}/BrowserPlugins/BrowserAdapterData
    install -m 644 -p ${S}/data/fl-win-shdw-top-right-corner.png ${D}${libdir}/BrowserPlugins/BrowserAdapterData
}
