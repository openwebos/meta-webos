# Copyright (c) 2012 Hewlett-Packard Development Company, L.P.

DESCRIPTION = "Utility for drawing progress to the frame buffer"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"
SECTION = "webos/base"

PR = "r0"

inherit webos_component
inherit webos_public_repo
inherit webos_cmake
inherit webos_machine_dep
inherit webos_enhanced_submissions

WEBOS_GIT_TAG = "submissions/${WEBOS_SUBMISSION}"
SRC_URI = "${OPENWEBOS_GIT_REPO}/${PN};tag=${WEBOS_GIT_TAG};protocol=git"
S = "${WORKDIR}/git"
