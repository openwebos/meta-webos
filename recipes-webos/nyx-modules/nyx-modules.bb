# (c) Copyright 2012  Hewlett-Packard Development Company, L.P. 

DESCRIPTION = "webOS portability layer - device-specific modules"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"
SECTION = "platform"

DEPENDS = "nyx-lib glib-2.0 luna-service2"

PR = "r1"

inherit cmake
inherit pkgconfig
inherit webos_enhanced_submissions

WEBOS_GIT_TAG = "submissions/${WEBOS_SUBMISSION}"
SRC_URI = "${OPENWEBOS_GIT_REPO}/${PN};tag=${WEBOS_GIT_TAG};protocol=git"
S = "${WORKDIR}/git"

OECMAKE_SOURCEPATH = "${S}"
OECMAKE_BUILDPATH = "BUILD-${MACHINE}"

EXTRA_OECMAKE += "-DMACHINE=${MACHINE} -DNYX_COMPONENT_VERSION:STRING=${PV} -DTARGET:STRING=rockhopper" 
EXTRA_OEMAKE += "-C ${OECMAKE_BUILDPATH}"

do_compile() {
        oe_runmake all
}

do_install() {
        oe_runmake install DESTDIR="${D}"
}

# XXX Belongs in oe/classes/cmake.bbclass
do_clean_append() {
        s = bb.data.getVar('S', d, 1)
        buildpath = bb.data.getVar('OECMAKE_BUILDPATH', d, 1)
        if s and buildpath:
                tree = s + '/' + buildpath
                if os.path.exists(tree):
                        bb.note('removing ' + tree)
                        os.system('rm -rf ' + tree)
}

FILES_${PN} += "/lib/*/nyx/modules/*.module"
FILES_${PN}-dbg += "/lib/*/nyx/modules/.debug/*.module"
