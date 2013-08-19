# Copyright (c) 2013 LG Electronics, Inc.

SUMMARY = "A userspace service that provides access to the Open webOS database"
SECTION = "webos/base"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

# db8 is also the provider for mojodb
PROVIDES = "mojodb"

DEPENDS = "luna-service2 jemalloc icu pmloglib curl glib-2.0 leveldb boost"

PR = "r14"

# ensure leveldb is installed in image
RDEPENDS_${PN} = "leveldb"

inherit webos_component
inherit webos_public_repo
inherit webos_enhanced_submissions
inherit webos_cmake
inherit webos_system_bus
inherit webos_daemon
inherit webos_library
inherit webos_system_bus

EXTRA_OECMAKE += "-DWEBOS_CONFIG_BUILD_TESTS:BOOL=TRUE -DWEBOS_DB8_BACKEND:STRING=leveldb"

WEBOS_GIT_PARAM_TAG = "submissions/${WEBOS_SUBMISSION}"
SRC_URI = "${OPENWEBOS_GIT_REPO_COMPLETE}"
S = "${WORKDIR}/git"

FILES_${PN}-dbg += "${libdir}/${PN}/tests/.debug"
