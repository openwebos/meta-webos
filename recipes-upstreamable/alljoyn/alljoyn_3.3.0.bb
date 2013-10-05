# Copyright (c) 2013 LG Electronics, Inc.

SUMMARY = "AllJoyn Open Source Project"
DESCRIPTION = "open-source software framework developed by Qualcomm Innovation Center to enable peer-to-peer communication"
SECTION = "libs/network"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://NOTICE.txt;md5=c80318e464aeed6671097df52cae13eb"

DEPENDS = "openssl xulrunner"

PR = "r4"

SRC_URI = "https://www.alljoyn.org/sites/default/files/resources/alljoyn-${PV}-src.tgz;name=alljoyncore \
           file://alljoyn.pc \
           file://alljoyn.upstart \
           file://alljoyn.conf \
           file://alljoyn-3.3.0.patch \
           file://fix_cpu_consumption.patch"

SRC_URI[alljoyncore.md5sum] = "bef8485dd65e9a92387d21582be9323d"
SRC_URI[alljoyncore.sha256sum] = "5605f65abe4bb80b0946192bfb80d9d27838b9f1306227e76d46a714a17b439f"


S = "${WORKDIR}/alljoyn-${PV}-src"

export GECKO_BASE = "${STAGING_INCDIR}/xulrunner-sdk"

# The CPU and OS variables are used to set the build output directory in AllJoyn Makefile
EXTRA_OEMAKE = "CPU=x86 OS=linux VARIANT=release"

#
# Disable QA insanity checks that don't apply to this component
#
# ldflags: No GNU_HASH in the elf binary
INSANE_SKIP_${PN} += "ldflags"

do_install() {
    install -d ${D}${libdir}
    oe_libinstall -so -C ${S}/build/dist/lib liballjoyn      ${D}${libdir}

    install -d ${D}${sbindir}
    install -v ${S}/build/dist/bin/alljoyn-daemon ${D}${sbindir}

    install -d ${D}${includedir}/qcc/posix
    install -v -m 644 ${S}/build/dist/inc/qcc/posix/*.h ${D}${includedir}/qcc/posix
    install -v -m 644 ${S}/build/dist/inc/qcc/*.h ${D}${includedir}/qcc

    install -d ${D}${includedir}/alljoyn
    install -v -m 644 ${S}/build/dist/inc/alljoyn/*.h ${D}${includedir}/alljoyn

    install -d ${D}${webos_browserpluginsdir}
    install -v ${S}/build/dist/js/lib/libnpalljoyn.so ${D}${webos_browserpluginsdir}

    install -d ${D}${webos_pkgconfigdir}
    install -v -m 644 ${WORKDIR}/alljoyn.pc ${D}${webos_pkgconfigdir}

    install -d ${D}${webos_upstartconfdir}
    install -v -m 644 ${WORKDIR}/alljoyn.upstart ${D}${webos_upstartconfdir}/alljoyn

    install -d ${D}${sysconfdir}/init
    install -v -m 644 ${WORKDIR}/alljoyn.conf ${D}${sysconfdir}/init/
}

# The results of do_compile() are already stripped
INHIBIT_PACKAGE_STRIP = "1"

# Note that plugins are always unversioned.
FILES_${PN} += "${webos_browserpluginsdir}/*.so"
