# Copyright (c) 2012-2013 LG Electronics, Inc.

DESCRIPTION = "Implementation of the Open webOS SmartKey spell checking service using hunspell"
SECTION = "webos/base"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS = "libpbnjson cjson glib-2.0 luna-service2 icu hunspell luna-prefs boost"

WEBOS_VERSION = "3.0.0-11_517519129add67e56e70f0389cac751aaa6bd76d"
INC_PR = "r7"

inherit webos_component
inherit webos_public_repo
inherit webos_enhanced_submissions
inherit webos_system_bus
inherit webos_daemon
inherit webos_machine_dep

SRC_URI = "${OPENWEBOS_GIT_REPO_COMPLETE}"
S = "${WORKDIR}/git"

WEBOS_QMAKE_TARGET = "${MACHINE}"

do_install_append() {
    # until project file is fixed to generate install target in Makefile, we have to install manually
    install -d ${D}${bindir}
    install -v -m 750 ${B}/release-${WEBOS_QMAKE_TARGET}/com.palm.smartkey ${D}${bindir}

    install -d ${D}/${includedir}/smartkey
    install -v -m 0644 ${S}/Src/*.h ${D}/${includedir}/smartkey/

    install -d ${D}/${webos_prefix}/smartkey/DefaultData
    install -d ${D}/${webos_prefix}/smartkey/hunspell
    # install all directories except hunspell under DefaultData
    cp -ra ${S}/DefaultData/autoreplace* ${D}/${webos_prefix}/smartkey/DefaultData/
    cp -ra ${S}/DefaultData/locale ${D}/${webos_prefix}/smartkey/DefaultData/
    cp -ra ${S}/DefaultData/manufacturer ${D}/${webos_prefix}/smartkey/DefaultData/
    cp -ra ${S}/DefaultData/whitelist ${D}/${webos_prefix}/smartkey/DefaultData/

    cp -ra ${S}/DefaultData/hunspell ${D}/${webos_prefix}/smartkey
    install -v -m 0644 ${S}/hunspell/basicGrammar.aff ${D}/${webos_prefix}/smartkey/hunspell

    install -d ${D}/${sysconfdir}/init
    install -v -m 0644 ${S}/smartkey.conf.start ${D}/${sysconfdir}/init/smartkey.conf

    install -d ${D}/${webos_sysbus_pubservicesdir}
    install -v -m 0644 ${S}/service/com.palm.smartKey.service.pub ${D}/${webos_sysbus_pubservicesdir}/com.palm.smartKey.service
    install -d ${D}/${webos_sysbus_prvservicesdir}
    install -v -m 0644 ${S}/service/com.palm.smartKey.service.prv ${D}/${webos_sysbus_prvservicesdir}/com.palm.smartKey.service

    install -d ${D}/${webos_sysbus_prvrolesdir}
    install -v -m 0644 ${S}/service/com.palm.smartKey.json.prv ${D}/${webos_sysbus_prvrolesdir}/com.palm.smartKey.json
    install -d ${D}/${webos_sysbus_pubrolesdir}
    install -v -m 0644 ${S}/service/com.palm.smartKey.json.pub ${D}/${webos_sysbus_pubrolesdir}/com.palm.smartKey.json
}

PACKAGES += "${PN}-data"
FILES_${PN}-data += "${webos_prefix}/smartkey"

RDEPENDS_${PN} = "${PN}-data"
