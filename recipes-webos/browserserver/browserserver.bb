# Copyright 2012  Hewlett-Packard Development Company, L.P.

SUMMARY = "Web rendering daemon for the isis-browser"
SECTION = "webos/base"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS = "libpbnjson flex-native qt4-webos webkit-webos jemalloc glib-2.0 pmcertificatemgr webkit-supplemental luna-sysmgr-ipc-messages libpbnjson luna-service2 luna-webkit-api"
# libptmalloc3.so is preloaded by /etc/event.d/browerserver
RDEPENDS_${PN} = "ptmalloc3 isis-fonts"

PR = "r8"

inherit webos_public_repo
inherit webos_submissions
inherit webos_daemon
inherit webos_system_bus
inherit webos_machine_dep

WEBOS_GIT_TAG = "${WEBOS_SUBMISSION}"
SRC_URI = "${ISIS_PROJECT_GIT_REPO}/BrowserServer;tag=${WEBOS_GIT_TAG};protocol=git \
  file://0001-BrowserPage-work-around-for-int-to-pointer-cast-on-6.patch \
"
S = "${WORKDIR}/git"

PARALLEL_MAKE = ""

CXXFLAGS += "-DUSE_LUNA_SERVICE=1"
CXXFLAGS += "-DUSE_CERT_MGR=1"
CXXFLAGS += "-DSETUP_WEBOS_DEVICE_DIRECTORIES=1"
CXXFLAGS += "-DEXIT_AFTER_LAST_CLIENT=1"
CXXFLAGS += "-DBUILD_BACKUP_MANAGER=1"
CXXFLAGS += "-DBUILD_SSL_SUPPORT=1"

EXTRA_OEMAKE += "TARGET_ARCH=${TARGET_ARCH}"
EXTRA_OEMAKE += "STAGING_BINDIR_NATIVE=${STAGING_BINDIR_NATIVE}"
EXTRA_OEMAKE += "STAGING_INCDIR=${STAGING_INCDIR}"
EXTRA_OEMAKE += "STAGING_LIBDIR=${STAGING_LIBDIR}"
# INCLUDE_DIR is the parent directory containing the Qt headers
EXTRA_OEMAKE += "INCLUDE_DIR=${STAGING_INCDIR}"

do_install() {
    # This target only installs BrowserServer
    oe_runmake INSTALL_DIR=${D} install
    # XXX The install target in the Makefile installs BrowserServer with the wrong permissions [CFISH-930]
    chmod 750 ${D}${bindir}/BrowserServer

    # Install the headers and the static library (need to override the settings for
    # STAGING_INCDIR and STAGING_LIBDIR).
    # XXX Can't use "make stage" because Makefile doesn't install the BrowserServer
    # headers into a subdirectory.
    # oe_runmake STAGING_INCDIR=${D}${includedir} STAGING_LIBDIR=${D}${libdir} stage

    install -d ${D}${includedir}/Yap
    install -d ${D}${includedir}/BrowserServer

    # stage the headers
    install -v -m 444 Yap/YapDefs.h   ${D}${includedir}/Yap
    install -v -m 444 Yap/YapClient.h ${D}${includedir}/Yap
    install -v -m 444 Yap/YapPacket.h ${D}${includedir}/Yap
    install -v -m 444 Yap/YapProxy.h  ${D}${includedir}/Yap
    install -v -m 444 Yap/YapServer.h ${D}${includedir}/Yap
    install -v -m 444 Yap/ProcessMutex.h ${D}${includedir}/Yap
    install -v -m 444 Yap/OffscreenBuffer.h ${D}${includedir}/Yap
    install -v -m 444 Yap/BufferLock.h ${D}${includedir}/Yap
    install -v -m 444 Src/BrowserOffscreenInfo.h ${D}${includedir}/BrowserServer/BrowserOffscreenInfo.h
    install -v -m 444 Src/BrowserOffscreenCalculations.h ${D}${includedir}/BrowserServer/BrowserOffscreenCalculations.h
    install -v -m 444 Src/BrowserRect.h ${D}${includedir}/BrowserServer/BrowserRect.h
    install -v -m 444 Src/IpcBuffer.h ${D}${includedir}/BrowserServer/IpcBuffer.h

    # stage the static library
    install -d ${D}${libdir}
    install -v -m 444 release-${TARGET_ARCH}/libYap.a ${D}${libdir}/libYap.a

    install -d ${D}${webos_upstartconfdir}
    install -v -m 555 -p ${S}/browserserver ${D}${webos_upstartconfdir}/browserserver

    install -d ${D}${webos_sysconfdir}

    if [ -f BrowserServer.conf ]
    then
        install -v -m 755 -p ${S}/BrowserServer.conf ${D}${webos_sysconfdir}/BrowserServer.conf
    fi

    install -d ${D}${webos_sysconfdir}/browser
    install -v -m 644 ${S}/schema/*.schema ${D}${webos_sysconfdir}/browser

    #static backup registration
    install -d ${D}${webos_sysconfdir}/backup
    install -v -m 644 backup/com.palm.browserServer ${D}${webos_sysconfdir}/backup/com.palm.browserServer
}
