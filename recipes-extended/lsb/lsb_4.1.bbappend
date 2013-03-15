# (c) Copyright 2013 LG Electronics

PR_append = "webos1"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://fix-lsb_release-to-work-with-busybox-head-and-find.patch"

# This is something that should be configured in the build.
# NOTE: Only a placeholder. GF-2461 will define new build variable for this.
WEBOS_BUILD_NUMBER ?= "unofficial"

# NOTE: Only a placeholder. GF-2461 will define new build variable for this.
BUILD_BRANCH = "${@base_get_metadata_git_branch('.', None).strip()}"

BUILD_INFO_FILE = "${DISTRO}-release"

BUILD_DISTRIB_ID = "${@ '${WEBOS_TARGET_CORE_OS}'.capitalize()}"

do_install_append(){
    # Remove lsb-release file and directory created by parent recipe.
    rm -f ${D}${sysconfdir}/lsb-release
    rm -rf ${D}${sysconfdir}/lsb-release.d

    echo "${BUILD_DISTRIB_ID} release ${DISTRO_VERSION}-${WEBOS_BUILD_NUMBER} (${DISTRO}-${BUILD_BRANCH})" >> ${D}${sysconfdir}/${BUILD_INFO_FILE}
}
