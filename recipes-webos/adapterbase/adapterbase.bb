# Copyright (c) 2012-2014 LG Electronics, Inc.

SUMMARY = "A base class library for browser plugins loaded by Open webOS"
SECTION = "webos/devel"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS = "glib-2.0 npapi-headers"

# isis-project components don't have submissions
PE = "1"
PV = "0.2"
SRCREV = "8896cabe6b6f9d8e6a35e7c8899bea1ae8039487"
PR = "r6"

inherit webos_public_repo
inherit webos_library

WEBOS_REPO_NAME = "AdapterBase"
SRC_URI = "${ISIS_PROJECT_GIT_REPO_COMPLETE}"
S = "${WORKDIR}/git"

WEBOS_NO_STATIC_LIBRARIES_WHITELIST = "AdapterBase.a"

EXTRA_OEMAKE += "LUNA_STAGING=."

do_install() {
    install -d ${D}${includedir}
    install -v -m 444 AdapterBase.h ${D}${includedir}
    install -d ${D}${libdir}
    install -v -m 444 AdapterBase.a ${D}${libdir}
}

ALLOW_EMPTY_${PN} = "1"
