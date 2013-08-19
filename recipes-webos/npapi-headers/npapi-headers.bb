# Copyright (c) 2012-2013 LG Electronics, Inc.

SUMMARY = "The header files used to develop NPAPI plugins and browsers"
SECTION = "webos/devel"
LICENSE = "MPL-1.1"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MPL-1.1;md5=1d38e87ed8d522c49f04e1efe0fab3ab"

PR = "r4"

inherit webos_public_repo
inherit webos_enhanced_submissions
inherit webos_arch_indep

WEBOS_GIT_PARAM_TAG = "${WEBOS_SUBMISSION}"
SRC_URI = "${ISIS_PROJECT_GIT_REPO_COMPLETE}"
S = "${WORKDIR}/git"

do_compile() {
    # otherwise make is executed and headers staged in ${HOME}/ISIS_OUT/
    :
}

do_install() {
    # new location
    install -d ${D}${includedir}/webkit/npapi
    install -v -m 666 *.h ${D}${includedir}/webkit/npapi

    # legacy location
    # install -m 666 *.h ${D}${includedir}
    cd ${D}${includedir}
    ln -sv webkit/npapi/*.h .
}

ALLOW_EMPTY_${PN} = "1"
