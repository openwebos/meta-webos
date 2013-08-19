# Copyright (c) 2012-2013 LG Electronics, Inc.

SUMMARY = "An extensible object oriented component used to add service components to webOS"
SECTION = "webos/devel"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS = "librolegen glib-2.0 libpbnjson luna-service2"

PR = "r2"

inherit webos_component
inherit webos_public_repo
inherit webos_enhanced_submissions
inherit webos_cmake

WEBOS_GIT_PARAM_TAG = "submissions/${WEBOS_SUBMISSION}"
SRC_URI = "${OPENWEBOS_GIT_REPO_COMPLETE}"
S = "${WORKDIR}/git"

ALLOW_EMPTY_${PN} = "1"
