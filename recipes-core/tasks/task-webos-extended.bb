# (c) Copyright 2012  Hewlett-Packard Development Company, L.P. 

DESCRIPTION = "meta-webos components used in Open webOS"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

PR = "r4"

PACKAGES = "\
    ${PN} \
    ${PN}-dbg \
    ${PN}-dev \
    "

PACKAGE_ARCH = "${MACHINE_ARCH}"

ALLOW_EMPTY = "1"

RDEPENDS_${PN} = " \
    activitymanager \
    app-services \
    browser-adapter \
    com.palm.app.browser \
    configurator \
    core-apps \
    cpushareholder-stub \
    enyo-1.0 \
    fbprogress \
    filecache \
    foundation-frameworks \
    librdx-stub \
    loadable-frameworks \
    luna-applauncher \
    luna-init \
    luna-sysmgr \
    luna-sysservice \
    luna-systemui \
    luna-universalsearchmgr \
    mojolocation-stub \
    mojomail-imap \
    mojomail-pop \
    mojomail-smtp \
    mojoservice-frameworks \
    mojoservicelauncher \
    pmklogd \
    pmlogctl \
    pmlogdaemon \
    pmnetconfigmanager-stub \
    rdx-utils-stub \
    sleepd \
    storaged \
    underscore \
    webos-initscripts \
    webos-shutdownscripts \
"

# Unused components:
# glibcurl
# pmcertificatemgr
