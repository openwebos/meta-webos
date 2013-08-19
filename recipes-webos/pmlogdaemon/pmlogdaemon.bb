# Copyright (c) 2012-2013 LG Electronics, Inc.

SUMMARY = "Open webOS logging daemon"
SECTION = "webos/base"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS = "pmloglib zlib glib-2.0 librdx libpbnjson pmloglib-private luna-service2"
# show_disk_usage.sh script uses mktemp, find, xargs, and du, all of which are
# provided by busybox.
RDEPENDS_${PN} = "busybox"

PR = "r7"

inherit webos_component
inherit webos_public_repo
inherit webos_enhanced_submissions
inherit webos_cmake
inherit webos_daemon
inherit webos_system_bus

WEBOS_GIT_PARAM_TAG = "submissions/${WEBOS_SUBMISSION}"
SRC_URI = "${OPENWEBOS_GIT_REPO_COMPLETE}"
S = "${WORKDIR}/git"

FILES_${PN} += "${datadir}/PmLogDaemon"
