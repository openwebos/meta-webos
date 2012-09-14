# (c) Copyright 2012  Hewlett-Packard Development Company, L.P. 

DESCRIPTION = "webOS portability layer - library"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"
SECTION = "platform"

DEPENDS = "glib-2.0"

PR = "r1"

inherit cmake
inherit pkgconfig
inherit webos_enhanced_submissions

# Can override in branch.conf to fetch from HEAD.
WEBOS_GIT_TAG = "submissions/${WEBOS_SUBMISSION}"
SRC_URI = "${OPENWEBOS_GIT_REPO}/${PN};tag=${WEBOS_GIT_TAG};protocol=git"
S = "${WORKDIR}/git"

# After submission 56, we need to look in the component root for the main CMakelists.txt
OECMAKE_SOURCEPATH = "${@ '${S}/src' if ${WEBOS_SUBMISSION} <= 56 else '${S}'}"
OECMAKE_BUILDPATH = "${S}/BUILD-${PACKAGE_ARCH}"
WEBOS_PKGCONFIG_BUILDDIR = "${OECMAKE_BUILDPATH}"

EXTRA_OECMAKE += "-DNYX_COMPONENT_VERSION:STRING=${WEBOS_COMPONENT_VERSION} -DTARGET:STRING=rockhopper"
# Note : this is not necessary as we already in OECMAKE_BUILDPATH
# EXTRA_OEMAKE += "-C ${OECMAKE_BUILDPATH}"

# XXX Remove once using webos_cmake
do_clean_append() {
        s = bb.data.getVar('S', d, 1)
        buildpath = bb.data.getVar('OECMAKE_BUILDPATH', d, 1)
        if s and buildpath:
                tree = s + '/' + buildpath
                if os.path.exists(tree):
                        bb.note('removing ' + tree)
                        os.system('rm -rf ' + tree)
}
