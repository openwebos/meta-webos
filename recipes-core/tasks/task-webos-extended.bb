#
# (c) Copyright 2012  Hewlett-Packard Development Company, L.P. 
#

DESCRIPTION = "Test apps task for WebOS"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

PACKAGES = "\
    ${PN} \
    ${PN}-dbg \
    ${PN}-dev \
    "

PACKAGE_ARCH = "${MACHINE_ARCH}"

ALLOW_EMPTY = "1"

RDEPENDS_${PN} = " \
    cjson \
    enyo-1.0 \
    foundation-frameworks \
    glibcurl \
    jemalloc \
    libpalmsocket \
    libpbnjson \
    librdx-stub \
    librolegen \
    libsandbox \
    loadable-frameworks \
    luna-init \
    luna-prefs \
    luna-service2 \
    luna-sysmgr-ipc \
    luna-sysmgr-ipc-messages \
    luna-webkit-api \
    mojoloader \
    mojoservicelauncher \
    mojoservice-frameworks \
    nodejs \
    nodejs-module-webos-dynaload \
    nodejs-module-webos-pmlog \
    nodejs-module-webos-sysbus \
    nyx-lib \
    pmcertificatemgr \
    pmloglib \
    pmstatemachineengine \
    powerd \
    qt4-webos \
    sleepd \
    storaged \
    underscore \
    webkit \
"
#        luna-universalsearchmgr ( broken )
#        filecache ( missing dependency mojodb )
#        activitymanager ( missing dependency mojodb )
#        pmlogconf ( needed in runtime for pmloglib )	
#        serviceinstaller (todo)
#        webkit-supplemental (todo)
#        adapterbase  (todo)
#        npapi-headers  (todo)
#        db8  (todo)

DEPENDS_${PN} = " \
     cmake-modules-webos-native \
"
