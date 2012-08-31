# (c) Copyright 2012  Hewlett-Packard Development Company, L.P. 

DESCRIPTION = "webOS portability layer - library"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"
SECTION = "platform"

DEPENDS = "glib-2.0"

inherit cmake
inherit pkgconfig

PR = "${WEBOS_SUBMISSION}"
PV = "${WEBOS_COMPONENT_VERSION}"

# Can override in branch.conf to fetch from HEAD.
WEBOS_GIT_TAG = "submissions/${WEBOS_SUBMISSION}"
SRC_URI = "${OPENWEBOS_GIT_REPO}/${PN};tag=${WEBOS_GIT_TAG};protocol=git"
S = "${WORKDIR}/git"

# After submission 56, we need to look in the component root for the main CMakelists.txt
OECMAKE_SOURCEPATH = "${@ '${S}/src' if ${PR} <= 56 else '${S}'}"
OECMAKE_BUILDPATH = "BUILD-${PACKAGE_ARCH}"
WEBOS_PKGCONFIG_BUILDDIR = "${OECMAKE_BUILDPATH}"

EXTRA_OECMAKE += "-DNYX_COMPONENT_VERSION:STRING=${PV} -DTARGET:STRING=rockhopper"
# Note : this was not necessary as we already in OECMAKE_BUILDPATH
# EXTRA_OEMAKE += "-C ${OECMAKE_BUILDPATH}"

# Remove
# EXTRA_OECMAKE += " -DTARGET:STRING=${OPENWEBOS_TARGET}"

#do_compile() {
#        oe_runmake all
#}

#do_install() {
#        oe_runmake DESTDIR="${D}" install
#}

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
