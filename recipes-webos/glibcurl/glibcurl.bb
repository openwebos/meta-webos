# Copyright (c) 2012-2013 LG Electronics, Inc.

SUMMARY = "Open webOS edition of the open-source glibcurl library"
SECTION = "libs"
LICENSE = "MIT BSD"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302 \
                    file://${COMMON_LICENSE_DIR}/BSD;md5=3775480a712fc46a69647678acb234cb \
"

DEPENDS = "glib-2.0 curl"

PR = "r1"

inherit webos_upstream_from_repo
inherit webos_public_repo
inherit webos_enhanced_submissions
inherit webos_cmake
inherit webos_library

WEBOS_GIT_PARAM_TAG = "submissions/${WEBOS_SUBMISSION}"
SRC_URI = "${OPENWEBOS_GIT_REPO_COMPLETE}"
S = "${WORKDIR}/git"
