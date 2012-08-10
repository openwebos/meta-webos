# (c) Copyright 2012  Hewlett-Packard Development Company, L.P.

SUMMARY = "Open webOS edition of the WebKit web rendering engine"
SECTION = "libs"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM =  "file://Source/WebCore/LICENSE-LGPL-2.1;md5=a778a33ef338abbaf8b8a7c36b6eec80"

DEPENDS = "qt4-webos qmake-webos-native luna-service2 sqlite3"

PR = "r9"

inherit webos_public_repo
inherit webos_qmake
inherit webos_submissions
inherit webos_library
inherit webos_machine_dep

# Webkit source is identified by WEBOS_SUBMISSION extracted from PREFERRED_VERSION_${PN}.
SRC_URI = "${ISIS_PROJECT_DOWNLOAD}/WebKit/WebKit_${WEBOS_SUBMISSION}s.zip"

# XXX Expediently patch Tools/Scripts/webkitdirs.pm to remove the
# extraneous empty "-W1,-soname," that qmake adds to link command lines.
# Eventually, arrange for qmake to do the right thing.
SRC_URI += "file://remove-empty-soname-arg.patch"

SRC_URI[md5sum] = "ed3995d6dd54a81c4db5d9ea92f30ef4"
SRC_URI[sha256sum] = "7835d2cb953724f67670e421a614022767865fa9bfc52f8bdccbc95605be8b4e"

S = "${WORKDIR}/isis-project-WebKit"
# The archive downloaded has the source in a subdirectory named "isis-project-WebKit-<commitID>".
# Appending WEBOS_ARCHIVE_ROOT_GLOB_SUFFIX to S and globbing it will expand to the actual
# extraction path -- see do_unpack_{prepend,append} below.
WEBOS_ARCHIVE_ROOT_GLOB_SUFFIX = "-*"

PALM_CC_OPT = "-O2"
OBJDIR = "${MACHINE}-${TARGET_ARCH}"
export WEBKITOUTPUTDIR = "${S}/WebKitBuild/${OBJDIR}"
PALM_BUILD_DIR = "${WEBKITOUTPUTDIR}/Release"


# Python code for do_unpack() uses tabs, so we need to as well.
python do_unpack_prepend () {
    import os

    s = d.getVar('S', True)
    glob_suffix = d.getVar('WEBOS_ARCHIVE_ROOT_GLOB_SUFFIX', True)
    os.system("rm -rf " + s + " " + s + glob_suffix)
}

python do_unpack_append () {
    os.system("mv -v " + s + glob_suffix + " " + s)
}


# Unfortunately, build-webkit does the configure and compile steps indivisibly.
do_configure() {
    :
}


do_compile() {
    # QTDIR needs to be defined, but its value doesn't matter.
    export QTDIR=unused

    # Use gold to link if it's available
    gold=${STAGING_BINDIR_NATIVE}/../libexec/gcc/arm-none-linux-gnueabi/4.5.1/gold
    if [ -e $gold ]; then
        QMAKE_LINK_ARGS='--qmakearg="QMAKE_LINK=\"${CXX} -B $gold -Wl,-debug\""'
    else
        QMAKE_LINK_ARGS=
    fi

    # Must define QT_SHARED; otherwise the routines in qt/Api aren't exported.
    # (How did we get away with not having this before qmake was a separate component?)
    ${S}/Tools/Scripts/build-webkit --qt \
        --release \
        --no-video \
        --no-webgl \
        --only-webkit \
        --no-webkit2 \
        --makeargs="${PARALLEL_MAKE}" \
        --qmake="${QMAKE}" \
        --qmakearg="DEFINES+=QT_SHARED" \
        --qmakearg="DEFINES+=PALM_DEVICE" \
        --qmakearg="DEFINES+=ENABLE_PALM_SERVICE_BRIDGE=1" \
        $QMAKE_LINK_ARGS
}


do_install() {
    # WebKit stages header files that include other header files. We can't just
    # copy their staged header files because the path won't be correct so we
    # have to copy the actual header file that is referenced.
    install -d ${D}${includedir}/QtWebKit
    install -v -m 444 ${PALM_BUILD_DIR}/include/QtWebKit/Q* ${D}${includedir}/QtWebKit
    (cd ${PALM_BUILD_DIR}/include/QtWebKit && perl -e 'while (<>) {if (m/^#include "([^"]+)"/) {print `install -v -m 444 $1 ${D}${includedir}/QtWebKit`;}}' q*.h)

    install -d ${D}${libdir}
    oe_libinstall -C ${PALM_BUILD_DIR}/lib -so libQtWebKit ${D}${libdir}

    # NB. QT_CONFIGURE_IMPORTS_PATH is set in webos_qmake.bbclass
    install -d ${D}${QT_CONFIGURE_IMPORTS_PATH}/QtWebKit
    install -v -m 555 ${PALM_BUILD_DIR}/imports/QtWebKit/* ${D}${QT_CONFIGURE_IMPORTS_PATH}/QtWebKit
}


addtask makeclean
do_makeclean() {
    if [ -d ${WEBKITOUTPUTDIR} -a -x ${S}/Tools/Scripts/build-webkit ]; then
        # XXX Why this is done when the whole directory will be removed by the next statement?
        ${S}/Tools/Scripts/build-webkit --qt \
            --release \
            --clean \
            --qmake="${QMAKE}" || true

        rm -rf ${WEBKITOUTPUTDIR}
    fi
}


FILES_${PN} += "${libdir}/libQtWebKit.so*"
FILES_${PN} += "${QT_CONFIGURE_IMPORTS_PATH}/QtWebKit/qmldir"
FILES_${PN} += "${QT_CONFIGURE_IMPORTS_PATH}/QtWebKit/libqmlwebkitplugin.so"
FILES_${PN}-dbg += "${libdir}/.debug"
FILES_${PN}-dbg += "${QT_CONFIGURE_IMPORTS_PATH}/QtWebKit/.debug"
