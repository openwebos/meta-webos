# Copyright (c) 2012-2013 Hewlett-Packard Development Company, L.P.
# Copyright (c) 2013 LG Electronics, Inc.

DESCRIPTION = "meta-webos components used in Open webOS"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

PR = "r17"

inherit packagegroup

PACKAGE_ARCH = "${MACHINE_ARCH}"

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

VIRTUAL-RUNTIME_webappmanager ?= "webappmanager"
VIRTUAL-RUNTIME_librdx ?= "librdx-stub"
VIRTUAL-RUNTIME_rdx-utils ?= "rdx-utils-stub"

# we're not using VIRTUAL-RUNTIME because VIRTUAL-RUNTIME is usually used for only one item
# and changing that in <distro>-preferred-providers.inc would require .bbappend in meta-<distro>
# to do PR/PRINC/PR_append bump anyway so it's easier to change this variable in .bbappend together
# with bump
# browserserver, webkit-supplemental, webkit-webos should be added to RDEPENDS of top level components
# which need them
WEBOS_PACKAGESET_BROWSER = " \
    browser-adapter \
    com.palm.app.browser \
    browserserver \
    webkit-supplemental \
    webkit-webos \
"

RDEPENDS_${PN} = " \
    activitymanager \
    app-services \
    configurator \
    core-apps \
    enyo-1.0 \
    filecache \
    keyboard-efigs \
    ${VIRTUAL-RUNTIME_librdx} \
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
    novacomd \
    nyx-modules \
    pmklogd \
    pmlogctl \
    pmlogdaemon \
    sleepd \
    ${VIRTUAL-RUNTIME_webappmanager} \
    webos-connman-adapter \
    webos-shutdownscripts \
    ${WEBOS_PACKAGESET_BROWSER} \
    ${WEBOS_MISSING_FROM_RDEPENDS} \
    ${WEBOS_FOSS_MISSING_FROM_RDEPENDS} \
"

# XXX These non-top-level components must be explicitly added because they are
# missing from the RDEPENDS lists of the components that expect them to be
# present at runtime.
WEBOS_MISSING_FROM_RDEPENDS = " \
    cpushareholder-stub \
    fbprogress \
    foundation-frameworks \
    loadable-frameworks \
    mojoservice-frameworks \
    nodejs \
    ${VIRTUAL-RUNTIME_rdx-utils} \
    underscore \
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
    lsb \
    makedevs \
    ncurses \
    openssl \
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
