# (c) Copyright 2012  Hewlett-Packard Development Company, L.P.

DESCRIPTION = "Abstraction JSON DOM & SAX layer for C/C++"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"
SECTION = "webos/libs"

DEPENDS = "yajl"

PR = "r1"

inherit webos_cmake
inherit webos_public_repo
inherit webos_enhanced_submissions
inherit webos_library

# These are the defaults, but explicity specify so that readers know they exist
EXTRA_OECMAKE += "-DWITH_DOCS:BOOL=FALSE -DWITH_TESTS:BOOL=FALSE"

WEBOS_GIT_TAG = "submissions/${WEBOS_SUBMISSION}"
SRC_URI = "${OPENWEBOS_GIT_REPO}/${PN};tag=${WEBOS_GIT_TAG};protocol=git"
S = "${WORKDIR}/git"
