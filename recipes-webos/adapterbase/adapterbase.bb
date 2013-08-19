# Copyright (c) 2012-2013 LG Electronics, Inc.

SUMMARY = "A base class library for browser plugins loaded by Open webOS"
SECTION = "webos/devel"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS = "glib-2.0 npapi-headers"

PR = "r4"

inherit webos_public_repo
inherit webos_enhanced_submissions
inherit webos_library

WEBOS_GIT_PARAM_TAG = "${WEBOS_SUBMISSION}"
WEBOS_REPO_NAME = "AdapterBase"
SRC_URI = "${ISIS_PROJECT_GIT_REPO_COMPLETE}"
S = "${WORKDIR}/git"

EXTRA_OEMAKE += "LUNA_STAGING=."

do_install() {
    install -d ${D}${includedir}
    install -v -m 444 AdapterBase.h ${D}${includedir}
    install -d ${D}${libdir}
    install -v -m 444 AdapterBase.a ${D}${libdir}
}

ALLOW_EMPTY_${PN} = "1"
