# Copyright (c) 2013 LG Electronics, Inc.

SUMMARY = "AllSeen Alliance Open Source Project"
DESCRIPTION = "The AllSeen Alliance framework is initially based on the AllJoyn™ open source project, and will be expanded with contributions from member companies and the open source community"
SECTION = "libs/network"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://alljoyn_c/src/AuthListener.cc;beginline=7;endline=21;md5=92223cc8faae258e0802873e2cf5f579"

DEPENDS = "openssl xulrunner"

PR = "r0"

SRC_URI = "https://git.allseenalliance.org/cgit/core/alljoyn.git/snapshot/alljoyn-legacy-03.04.05-generated.tar.gz;name=alljoyncore \
           file://alljoyn.pc \
           file://alljoyn.upstart \
           file://alljoyn.conf \
           file://makefile-fixes-for-compiling-in-OE.patch \
           file://fix-cpu-usage-when-there-are-many-nodes.patch \
           file://reduce-traffic-and-memory-for-sessionless-signals.patch \
           file://reduce-cpu-usage-during-stress-scenario.patch "

SRC_URI[alljoyncore.md5sum] = "4291df9346caf5367b6c147f62d616f2"
SRC_URI[alljoyncore.sha256sum] = "1559d6cc99a7192ac9e228e92ffdc9fe6fbc3569ccfc68969172128551ced49c"

S = "${WORKDIR}/alljoyn-legacy-03.04.05-generated"

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
