# (c) Copyright 2012  Hewlett-Packard Development Company, L.P. 

SUMMARY = "Stubbed implementation of the webOS Remote Diagnostics Utilities"
SECTION = "webos/base"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

RPROVIDES = "rdx-utils"

PR = "r1"

inherit webos_component
inherit webos_public_repo
inherit webos_enhanced_submissions
inherit webos_cmake
inherit allarch
inherit webos_program

WEBOS_GIT_TAG = "submissions/${WEBOS_SUBMISSION}" 
SRC_URI = "${OPENWEBOS_GIT_REPO}/${PN};tag=${WEBOS_GIT_TAG};protocol=git"
S = "${WORKDIR}/git"
