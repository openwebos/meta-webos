# Copyright 2012  Hewlett-Packard Development Company, L.P.

DESCRIPTION = "This is the web rendering process for the isis-browser."
LICENSE = "Apache-2.0"
SECTION = "webos/devel"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS = "flex-native qt4-webos webkit-webos jemalloc glib-2.0 pmcertificatemgr webkit-supplemental luna-sysmgr-ipc-messages"
# libptmalloc3.so is preloaded by /etc/event.d/browerserver
RDEPENDS_${PN} = "ptmalloc3"

PR = "r1"

inherit autotools
inherit pkgconfig
inherit webos_public_repo
inherit webos_submissions

WEBOS_GIT_TAG = "${WEBOS_SUBMISSION}"
SRC_URI = "${ISIS-PROJECT_GIT_REPO}/BrowserServer;tag=${WEBOS_GIT_TAG};protocol=git"
S = "${WORKDIR}/git"

PARALLEL_MAKE = ""

CXXFLAGS += "-DUSE_LUNA_SERVICE=1"
CXXFLAGS += "-DUSE_CERT_MGR=1"
CXXFLAGS += "-DSETUP_WEBOS_DEVICE_DIRECTORIES=1"
CXXFLAGS += "-DEXIT_AFTER_LAST_CLIENT=1"
CXXFLAGS += "-DBUILD_BACKUP_MANAGER=1"
CXXFLAGS += "-DBUILD_SSL_SUPPORT=1"

runcmd() {
        #Display the command output
        echo $*
        $*
}

do_compile_prepend() {
        export TARGET_ARCH="${TARGET_ARCH}" # This is needed to create object directory
        export STAGING_BINDIR_NATIVE="${STAGING_BINDIR_NATIVE}"
        export STAGING_INCDIR="${STAGING_INCDIR}"
        export INCLUDE_DIR="${PKG_CONFIG_SYSROOT_DIR}/include"
        export STAGING_LIBDIR="${STAGING_LIBDIR}"
}

do_install_prepend() {
        export INSTALL_DIR="${D}"
        export TARGET_ARCH="${TARGET_ARCH}" # This is needed to create object directory
        runcmd install -d ${D}${includedir}/Yap
        runcmd install -d ${D}${includedir}/BrowserServer

        # stage the headers
        runcmd install -m 444 Yap/YapDefs.h   ${D}${includedir}/Yap
        runcmd install -m 444 Yap/YapClient.h ${D}${includedir}/Yap
        runcmd install -m 444 Yap/YapPacket.h ${D}${includedir}/Yap
        runcmd install -m 444 Yap/YapProxy.h  ${D}${includedir}/Yap
        runcmd install -m 444 Yap/YapServer.h ${D}${includedir}/Yap
        runcmd install -m 444 Yap/ProcessMutex.h ${D}${includedir}/Yap
        runcmd install -m 444 Yap/OffscreenBuffer.h ${D}${includedir}/Yap
        runcmd install -m 444 Yap/BufferLock.h ${D}${includedir}/Yap
        runcmd install -m 444 Src/BrowserOffscreenInfo.h ${D}${includedir}/BrowserServer/BrowserOffscreenInfo.h
        runcmd install -m 444 Src/BrowserOffscreenCalculations.h ${D}${includedir}/BrowserServer/BrowserOffscreenCalculations.h
        runcmd install -m 444 Src/BrowserRect.h ${D}${includedir}/BrowserServer/BrowserRect.h
        runcmd install -m 444 Src/IpcBuffer.h ${D}${includedir}/BrowserServer/IpcBuffer.h

        # stage the static library
        runcmd install -d ${D}${libdir} 
        runcmd install -m 444 release-${TARGET_ARCH}/libYap.a ${D}${libdir}/libYap.a
}

do_install_append() {
        install -d ${D}${sysconfdir}/event.d
        install -m 555 -p ${S}/browserserver ${D}${sysconfdir}/event.d/browserserver

        install -d ${D}${sysconfdir}/palm

        if [ -f BrowserServer.conf ]
        then
                install -m 755 -p ${S}/BrowserServer.conf ${D}${sysconfdir}/palm/BrowserServer.conf
        fi

        install -d ${D}${sysconfdir}/palm/browser
        install -m 644 ${S}/schema/*.schema ${D}${sysconfdir}/palm/browser

        #static backup registration
        install -d ${D}${sysconfdir}/palm/backup
        install -m 644 backup/com.palm.browserServer ${D}${sysconfdir}/palm/backup/com.palm.browserServer

        chmod o-rwx ${D}${bindir}/BrowserServer
}
