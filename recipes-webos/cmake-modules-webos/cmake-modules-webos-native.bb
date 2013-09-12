# Copyright (c) 2012-2013 LG Electronics, Inc.

DESCRIPTION = "CMake modules used by webOS"
LICENSE = "Apache-2.0"
SECTION = "webos/devel/tools"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

WEBOS_VERSION = "1.0.0~rc5-16_5d5084fca6179b6365b7c8130809ce66f441bb30"
PR = "r0"

inherit webos_component
inherit webos_public_repo
inherit webos_enhanced_submissions
inherit webos_arch_indep
inherit native
# Can't inherit from webos_cmake as it introduces a circular dependency
inherit cmake

# There should always be a CMakeLists.txt in the root of the project
OECMAKE_SOURCEPATH = "${S}"

# do_clean() assumes this != ${S}
OECMAKE_BUILDPATH = "${S}/BUILD-${PACKAGE_ARCH}"

WEBOS_PKGCONFIG_BUILDDIR = "${OECMAKE_BUILDPATH}"

EXTRA_OECMAKE += "-DWEBOS_INSTALL_ROOT:PATH=/"

# XXX Should error if WEBOS_COMPONENT_VERSION is unset
EXTRA_OECMAKE += "-DWEBOS_COMPONENT_VERSION:STRING=${WEBOS_COMPONENT_VERSION}"

# Make needs to know that build directory is a subdirectory
EXTRA_OEMAKE += "-C ${OECMAKE_BUILDPATH}"

SRC_URI = "${OPENWEBOS_GIT_REPO_COMPLETE}"
S = "${WORKDIR}/git"

do_compile() {
     :
}

do_clean_append() {
    buildpath = bb.data.getVar('OECMAKE_BUILDPATH', d, 1)
    if buildpath and os.path.exists(buildpath):
        bb.note('removing ' + buildpath)
        os.system('rm -rf ' + buildpath)
}

BBCLASSEXTEND = "native"

