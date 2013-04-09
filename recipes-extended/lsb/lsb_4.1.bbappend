# Copyright (c) 2013  LG Electronics, Inc.

PR_append = "webos3"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://fix-lsb_release-to-work-with-busybox-head-and-find.patch"

WEBOS_TARGET_CORE_OS ?= "undefined"
BUILD_INFO_FILE = "${DISTRO}-release"
BUILD_DISTRIB_ID = "${@'${WEBOS_TARGET_CORE_OS}'.capitalize()}"

do_install_append() {
    # Remove lsb-release file and directory created by parent recipe.
    rm -f ${D}${sysconfdir}/lsb-release
    rm -rf ${D}${sysconfdir}/lsb-release.d

    echo "${BUILD_DISTRIB_ID} release ${DISTRO_VERSION}-${WEBOS_DISTRO_BUILD_ID} (${WEBOS_DISTRO_RELEASE_CODENAME})" > ${D}${sysconfdir}/${BUILD_INFO_FILE}
}
