# Copyright (c) 2012-2013 LG Electronics, Inc.

SUMMARY = "JavaScript loader for foundation frameworks and other loadable libraries"
SECTION = "webos/frameworks"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

WEBOS_VERSION = "1.1-11_52b9fd0f93b631ecb29f69f6475b85b073a72e16"
PR = "r5"

#inherit webos_component
inherit webos_public_repo
inherit webos_enhanced_submissions
#inherit webos_cmake
inherit webos_arch_indep

SRC_URI = "${OPENWEBOS_GIT_REPO_COMPLETE}"
S = "${WORKDIR}/git"

do_install() {
    install -d ${D}${webos_frameworksdir}
    install -v -m 0644  ${S}/mojoloader.js ${D}${webos_frameworksdir}
}

FILES_${PN} += "${webos_frameworksdir}"
