# (c) Copyright 2012-2013 Hewlett-Packard Development Company, L.P.

SUMMARY = "Library containing common parts of luna-sysmgr and webappmanager"
SECTION = "webos/libs"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS = "qt4-webos glib-2.0 luna-prefs luna-service2 cjson nyx-lib libpbnjson luna-webkit-api luna-sysmgr-ipc luna-sysmgr-ipc-messages sqlite3 pmloglib librolegen serviceinstaller"

PR = "r3"

# Don't uncomment until all of the do_*() tasks have been moved out of the recipe
#inherit webos_component
inherit webos_public_repo
inherit webos_qmake
inherit webos_enhanced_submissions
inherit webos_library
inherit webos_machine_dep

WEBOS_GIT_TAG = "submissions/${WEBOS_SUBMISSION}"
SRC_URI = "${OPENWEBOS_GIT_REPO}/${PN};tag=${WEBOS_GIT_TAG}"
S = "${WORKDIR}/git"

EXTRA_OEMAKE += "MACHINE=${MACHINE}"

do_configure() {
    MACHINE=${MACHINE} ${QMAKE}

    # We want the shared libraries to have an SONAME records => remove the empty -Wl,-soname,
    # argument that qmake adds (why is it doing this?).
    find . -name Makefile | xargs sed -i -e 's/-Wl,-soname, //' -e 's/-Wl,-soname,$//'
}

do_install() {
    oe_runmake install

    install -d ${D}${libdir}
    install -d ${D}${includedir}/luna-sysmgr-common

    oe_libinstall -C release-${MACHINE} -so libLunaSysMgrCommon ${D}${libdir}

    install -v -m 644 ${S}/include/*.h ${D}${includedir}/luna-sysmgr-common
}
