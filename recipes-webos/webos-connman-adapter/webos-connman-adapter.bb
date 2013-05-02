# Copyright (c) 2012-2013 LG Electronics, Inc.

DESCRIPTION = "Open webOS component for managing network connections using connman"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"
SECTION = "webos/services"

DEPENDS = "luna-service2 libpbnjson glib-2.0 luna-prefs openssl"
RDEPENDS_${PN} = "connman"

WEBOS_VERSION = "1.0.0-10_81b7d1370a7bf7449f47e24fa1df21b21143a8ba"
PR = "r1"

inherit webos_component
inherit webos_public_repo
inherit webos_enhanced_submissions
inherit webos_cmake
inherit webos_daemon
inherit webos_system_bus
inherit webos_machine_dep

# Set EXTRA_OECMAKE in webos-connman-adapter.bbappend to override default value for wifi and wired interfaces, for eg.
# EXTRA_OECMAKE += "-DWIFI_IFACE_NAME=wlan0 -DWIRED_IFACE_NAME=eth1"

SRC_URI = "${OPENWEBOS_GIT_REPO_COMPLETE}"
S = "${WORKDIR}/git"
