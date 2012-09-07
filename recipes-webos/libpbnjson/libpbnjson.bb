# (c) Copyright 2012  Hewlett-Packard Development Company, L.P.

DESCRIPTION = "Abstraction JSON DOM & SAX layer for C/C++"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"
SECTION = "webos/libs"

PROVIDES = "libpbnjson"

DEPENDS = "yajl"

inherit webos_cmake
inherit webos_public_repo
inherit webos_enhanced_submissions

WEBOS_GIT_TAG = "${WEBOS_SUBMISSION}"
SRC_URI = "${OPENWEBOS_GIT_REPO}/${PN};tag=${WEBOS_GIT_TAG};protocol=git"
S = "${WORKDIR}/git/src"

# Don't build unit tests
EXTRA_OECMAKE += "-DWITH_TESTS=FALSE -DWITH_DOCS=FALSE -DWITH_V8=FALSE -DWITH_BENCHMARKS=FALSE"

