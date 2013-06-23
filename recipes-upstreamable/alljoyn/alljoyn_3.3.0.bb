# Copyright (c) 2013 LG ELectronics, Inc.

SUMMARY = "AllJoyn Open Source Project"
SECTION = "libs/network"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://NOTICE.txt;md5=c80318e464aeed6671097df52cae13eb"

DEPENDS = "openssl"

PR = "r0"

# Remove xulrunner fetch once [GF-7675] is implemented.
SRC_URI = "https://www.alljoyn.org/sites/default/files/resources/alljoyn-${PV}-src.tgz;name=alljoyncore \
           http://ftp.mozilla.org/pub/mozilla.org/xulrunner/releases/3.6.27/sdk/xulrunner-3.6.27.en-US.linux-i686.sdk.tar.bz2;name=xulrunner-sdk \
           file://alljoyn.pc \
           file://alljoyn.upstart \
           file://alljoyn-3.3.0.patch"

SRC_URI[alljoyncore.md5sum] = "bef8485dd65e9a92387d21582be9323d"
SRC_URI[alljoyncore.sha256sum] = "5605f65abe4bb80b0946192bfb80d9d27838b9f1306227e76d46a714a17b439f"

SRC_URI[xulrunner-sdk.md5sum] = "2fc380eb06874a1051468f84328bfddc"
SRC_URI[xulrunner-sdk.sha256sum] = "42812d23ebe0fda002926ea5820aa665ad76f5bb3b0fb7d723fba80ce9b6baa8"

S = "${WORKDIR}/alljoyn-${PV}-src"

export GECKO_BASE = "${WORKDIR}/xulrunner-sdk"
EXTRA_OEMAKE = "CPU=x86 OS=linux VARIANT=release"

#
# Disable QA insanity checks that don't apply to this component
#
# ldflags: No GNU_HASH in the elf binary
INSANE_SKIP_${PN} += "ldflags"

do_install() {
    install -d ${D}${libdir}
    install -v ${S}/build/dist/lib/liballjoyn.so ${D}${libdir}

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
}

# The results of do_compile() are already stripped
INHIBIT_PACKAGE_STRIP = "1"

# Until [GF-7676] is implemented, only an unversioned liballjoyn.so (without an
# SONAME) is built => the .so needs to go into ${PN} instead of ${PN}-dev. Note
# that plugins are always unversioned.
SOLIBS = "${SOLIBSDEV}"
FILES_SOLIBSDEV = ""
FILES_${PN} += "${webos_browserpluginsdir}/*.so"
