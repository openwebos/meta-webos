# (c) Copyright 2012  Hewlett-Packard Development Company, L.P. 
#
# webos_cmake
#
# This class is to be inherited by the recipe for every component that uses CMake
# and cmake-modules-webos.
#
# Expects that webos_submissions or webos_enhanced_submissions will also be inherited
# (for WEBOS_COMPONENT_VERSION).

DEPENDS += "cmake-modules-webos-native"

inherit cmake

# There should always be a CMakeLists.txt in the root of the project
OECMAKE_SOURCEPATH = "${S}"


# If inheriting from webos_machine_dep, then use a separate build directory for each value of MACHINE (as they'll be different).
# do_clean() assumes this != ${S}
OECMAKE_BUILDPATH = "${@ '${S}/BUILD-${MACHINE}' if bb.data.inherits_class('webos_machine_dep', d) else '${S}/BUILD-${PACKAGE_ARCH}' }"

WEBOS_PKGCONFIG_BUILDDIR = "${OECMAKE_BUILDPATH}"

EXTRA_OECMAKE += "-DWEBOS_INSTALL_ROOT:PATH=/"

# XXX Should error if WEBOS_COMPONENT_VERSION is unset
EXTRA_OECMAKE += "-DWEBOS_COMPONENT_VERSION:STRING=${WEBOS_COMPONENT_VERSION}"
EXTRA_OECMAKE += "${@ '-DWEBOS_TARGET_CORE_OS:STRING=${WEBOS_TARGET_CORE_OS}' if bb.data.inherits_class('webos_core_os_dep', d) else '' }"
# XXX Add webos_kernel_dep() to webOS.cmake that adds WEBOS_TARGET_KERNEL_HEADERS to the search path
EXTRA_OECMAKE += "${@ '-DWEBOS_TARGET_KERNEL_HEADERS:STRING=${STAGING_KERNEL_DIR}/include' if bb.data.inherits_class('webos_kernel_dep', d) else '' }"
EXTRA_OECMAKE += "${@ '-DWEBOS_TARGET_MACHINE:STRING=${MACHINE}' if bb.data.inherits_class('webos_machine_dep', d) else '' }"
EXTRA_OECMAKE += "${@ '-DWEBOS_TARGET_MACHINE_IMPL:STRING=${WEBOS_TARGET_MACHINE_IMPL}' if bb.data.inherits_class('webos_machine_impl_dep', d) else '' }"


# Make needs to know that build directory is a subdirectory
EXTRA_OEMAKE += "-C ${OECMAKE_BUILDPATH}"

do_configure() {
        cmake_do_configure
}

do_compile() {
        cmake_do_compile
}

do_install() {
        cmake_do_install
}

# Stage headers and libraries that are installed by CMakeLists.txt with the 
# "install" command.
# XXX Eventually write our own replacement for autotools_stage_all so that there's
#     no dependency on autotools
#do_stage() {
#	autotools_stage_all
#}

do_generate_toolchain_file() {
        cmake_do_generate_toolchain_file
}

do_clean_append() {
        buildpath = bb.data.getVar('OECMAKE_BUILDPATH', d, 1)
        if buildpath and os.path.exists(buildpath):
            bb.note('removing ' + buildpath)
            os.system('rm -rf ' + buildpath)
}
