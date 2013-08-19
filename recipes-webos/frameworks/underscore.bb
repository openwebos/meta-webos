# Copyright (c) 2012-2013 LG Electronics, Inc.

SUMMARY = "The underscore.js utility-belt library for JavaScript made into an Open webOS loadable framework"
SECTION = "webos/frameworks"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

PR = "r5"

#inherit webos_component
inherit webos_public_repo
inherit webos_enhanced_submissions
#inherit webos_cmake
inherit webos_arch_indep

WEBOS_GIT_PARAM_TAG = "submissions/${WEBOS_SUBMISSION}"
SRC_URI = "${OPENWEBOS_GIT_REPO_COMPLETE}"
S = "${WORKDIR}/git"

do_install() {
    install -d ${D}${webos_frameworksdir}/underscore/version/1.0/
    cp -vrf ${S}/* ${D}${webos_frameworksdir}/underscore/version/1.0/
}

FILES_${PN} += "${webos_frameworksdir}"
