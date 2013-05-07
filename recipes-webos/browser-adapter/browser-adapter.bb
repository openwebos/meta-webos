# Copyright (c) 2012-2013 LG Electronics, Inc.

SUMMARY = "NPAPI browser plugin to isis-browser"
SECTION = "webos/libs"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS = "adapterbase browserserver libpng npapi-headers glib-2.0 qt4-webos"

WEBOS_VERSION = "3.0.0-0.4_7c4cb757dcaac08a18e0ea1699e2c748710bdbc5"
PR = "r8"

inherit webos_public_repo
inherit webos_enhanced_submissions
inherit webos_library
inherit webos_machine_dep

WEBOS_GIT_REPO_TAG = "${WEBOS_SUBMISSION}"
WEBOS_REPO_NAME = "BrowserAdapter"
SRC_URI = "${ISIS_PROJECT_GIT_REPO_COMPLETE}"
S = "${WORKDIR}/git"

PARALLEL_MAKE = ""

EXTRA_OEMAKE += "TARGET_ARCH=${TARGET_ARCH}"
EXTRA_OEMAKE += "STAGING_INC_DIR=${STAGING_INCDIR}"
EXTRA_OEMAKE += "STAGING_LIB_DIR=${STAGING_LIBDIR}"
# The Qt headers are expected to be found under QT_INSTALL_PREFIX/include
EXTRA_OEMAKE += "QT_INSTALL_PREFIX=${STAGING_INCDIR}/.."


do_install() {
    # This target only installs BrowserAdapter.so
    oe_runmake INSTALL_DIR=${D}${prefix} install

    install -d ${D}${libdir}/BrowserPlugins/BrowserAdapterData
    install -v -m 644 -p ${S}/data/launcher-bookmark-alpha.png ${D}${libdir}/BrowserPlugins/BrowserAdapterData
    install -v -m 644 -p ${S}/data/launcher-bookmark-overlay.png ${D}${libdir}/BrowserPlugins/BrowserAdapterData
    install -v -m 644 -p ${S}/data/shift-tap-reticle.png ${D}${libdir}/BrowserPlugins/BrowserAdapterData

    install -v -m 644 -p ${S}/data/fl-win-shdw-bot.png ${D}${libdir}/BrowserPlugins/BrowserAdapterData
    install -v -m 644 -p ${S}/data/fl-win-shdw-bot-left-corner.png ${D}${libdir}/BrowserPlugins/BrowserAdapterData
    install -v -m 644 -p ${S}/data/fl-win-shdw-bot-right-corner.png ${D}${libdir}/BrowserPlugins/BrowserAdapterData
    install -v -m 644 -p ${S}/data/fl-win-shdw-left.png ${D}${libdir}/BrowserPlugins/BrowserAdapterData
    install -v -m 644 -p ${S}/data/fl-win-shdw-right.png ${D}${libdir}/BrowserPlugins/BrowserAdapterData
    install -v -m 644 -p ${S}/data/fl-win-shdw-top.png ${D}${libdir}/BrowserPlugins/BrowserAdapterData
    install -v -m 644 -p ${S}/data/fl-win-shdw-top-left-corner.png ${D}${libdir}/BrowserPlugins/BrowserAdapterData
    install -v -m 644 -p ${S}/data/fl-win-shdw-top-right-corner.png ${D}${libdir}/BrowserPlugins/BrowserAdapterData
}

FILES_${PN} += "${libdir}/BrowserPlugins/BrowserAdapter.so ${libdir}/BrowserPlugins/BrowserAdapterData/*.png"
FILES_${PN}-dbg += "${libdir}/BrowserPlugins/.debug/BrowserAdapter.so"
