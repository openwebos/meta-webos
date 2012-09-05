# (c) Copyright 2012  Hewlett-Packard Development Company, L.P. 

DESCRIPTION = "meta-webos components used in Open webOS"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

PR = "r7"

PACKAGES = "\
    ${PN} \
    ${PN}-dbg \
    ${PN}-dev \
"

PACKAGE_ARCH = "${MACHINE_ARCH}"

ALLOW_EMPTY = "1"

# to replace task-webos-extended by packagegroup-webos-extended during upgrade on target
RPROVIDES_${PN} = "task-webos-extended"
RREPLACES_${PN} = "task-webos-extended"
RCONFLICTS_${PN} = "task-webos-extended"

# task-webos-core was removed in 
# https://github.com/openwebos/meta-webos/commit/70b787bcb78db34c6a7d05b19786cb2e48bbece2
# this makes sure it's removed (replaced) by packagegroup-webos-extended on target device

RPROVIDES_${PN} += "task-webos-core"
RREPLACES_${PN} += "task-webos-core"
RCONFLICTS_${PN} += "task-webos-core"

# and once again for packagegroup-webos-core for those who have installed images with renamed task recipes
RPROVIDES_${PN} += "packagegroup-webos-core"
RREPLACES_${PN} += "packagegroup-webos-core"
RCONFLICTS_${PN} += "packagegroup-webos-core"

RDEPENDS_${PN} = " \
    activitymanager \
    app-services \
    browser-adapter \
    com.palm.app.browser \
    configurator \
    core-apps \
    enyo-1.0 \
    filecache \
    librdx-stub \
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
    mojoservicelauncher \
    pmklogd \
    pmlogctl \
    pmlogdaemon \
    pmnetconfigmanager-stub \
    sleepd \
    storaged \
    webos-initscripts \
    webos-shutdownscripts \
    ${WEBOS_MISSING_FROM_RDEPENDS} \
    ${WEBOS_FOSS_MISSING_FROM_RDEPENDS} \
"

# XXX These non-top-level components must be explicitly added because they are
# missing from the RDEPENDS lists of the components that expect them to be
# present at runtime.
WEBOS_MISSING_FROM_RDEPENDS = " \
    browserserver \
    cpushareholder-stub \
    fbprogress \
    foundation-frameworks \
    loadable-frameworks \
    mojoservice-frameworks \
    nodejs \
    rdx-utils-stub \
    underscore \
    webkit-supplemental \
    webkit-webos \
"

# XXX These FOSS components must be explicitly added because they are missing
# from the RDEPENDS lists of the components that expect them to be present at
# runtime (or perhaps some are in fact top-level components and some others
# aren't actually needed).
WEBOS_FOSS_MISSING_FROM_RDEPENDS = " \
    bash \
    binutils \
    bzip2 \
    curl \
    dhcp-client \
    e2fsprogs \
    gzip \
    hunspell \
    icu \
    iproute2 \
    makedevs \
    ncurses \
    openssl \
    opkg \
    parted \
    procps \
    psmisc \
    sqlite3 \
    sysvinit-pidof \
    tzdata \
"

# Unused meta-webos components:
# - glibcurl
# - libtinyxml

# Unused meta-oe components:
# - orc
