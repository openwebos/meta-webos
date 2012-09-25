# (c) Copyright 2012  Hewlett-Packard Development Company, L.P. 

DESCRIPTION = "meta-webos components used in Open webOS"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

PR = "r3"

PACKAGES = "\
    ${PN} \
    ${PN}-dbg \
    ${PN}-dev \
    "

PACKAGE_ARCH = "${MACHINE_ARCH}"

ALLOW_EMPTY = "1"

RDEPENDS_${PN} = " \
    adapterbase \
    browser-adapter \
    browserserver \
    activitymanager \
    app-services \
    cjson \
    com.palm.app.browser \
    configurator \
    core-apps \
    cpushareholder-stub \
    db8 \
    enyo-1.0 \
    fbprogress \
    filecache \
    foundation-frameworks \
    glibcurl \
    jemalloc \
    libpalmsocket \
    libpbnjson \
    librdx-stub \
    librolegen \
    libsandbox \
    loadable-frameworks \
    luna-applauncher \
    luna-init \
    luna-prefs \
    luna-service2 \
    luna-sysmgr \
    luna-sysmgr-ipc \
    luna-sysmgr-ipc-messages \
    luna-sysservice \
    luna-systemui \
    luna-universalsearchmgr \
    luna-webkit-api \
    mojoloader \
    mojolocation-stub \
    mojomail-common \
    mojomail-imap \
    mojomail-pop \
    mojomail-smtp \
    mojoservicelauncher \
    mojoservice-frameworks \
    nodejs \
    nodejs-module-webos-dynaload \
    nodejs-module-webos-pmlog \
    nodejs-module-webos-sysbus \
    npapi-headers \
    nyx-lib \
    nyx-modules \
    pmcertificatemgr \
    pmklogd \
    pmlogconf \
    pmlogdaemon \
    pmloglib \
    pmlogctl \
    pmnetconfigmanager-stub \
    pmstatemachineengine \
    powerd \
    rdx-utils-stub \
    qt4-webos \
    sleepd \
    storaged \
    underscore \
    webkit-supplemental \
    webkit-webos \
    webos-initscripts \
    webos-shutdownscripts \
"
#        serviceinstaller (todo)
