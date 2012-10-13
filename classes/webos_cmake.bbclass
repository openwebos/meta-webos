# (c) Copyright 2012  Hewlett-Packard Development Company, L.P. 
#
# webos_cmake
#
# This class is to be inherited by every recipe in meta-webos whose component
# uses CMake. It adds a dependency on cmake-modules-webos-native, which will be
# extraneous until the component is converted, but who cares? 
#
# Expects that webos_submissions or webos_enhanced_submissions will also be
# inherited (for WEBOS_COMPONENT_VERSION).

DEPENDS += "cmake-modules-webos-native"

inherit cmake

# There should always be a CMakeLists.txt in the root of the project
OECMAKE_SOURCEPATH = "${S}"


# If inheriting from webos_machine_dep, then use a separate build directory for
# each value of MACHINE (as they'll be different). Note that do_clean() assumes this != ${S}
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


# This information is always useful to have around
EXTRA_OECMAKE += "-Wdev"

# Fixup in case CMake files don't recognize the new value i586 for
# CMAKE_SYSTEM_PROCESSOR (e.g. nodejs)
do_generate_toolchain_file_append() {
    sed '/CMAKE_SYSTEM_PROCESSOR/ s/i586/i686/' -i ${WORKDIR}/toolchain.cmake
}


# Always invoke CMake from an empty build directory. Our CMakeLists.txt-s have
# not been written to handle incremental updates.
do_configure_prepend() {
    if [ $(readlink -f ${OECMAKE_SOURCEPATH}) != $(readlink -f ${OECMAKE_BUILDPATH}) ]; then
        bbnote "Removing ${OECMAKE_BUILDPATH}"
        rm -rf ${OECMAKE_BUILDPATH}
    fi
}

# Record how cmake was invoked
do_configure_append() {
    # Keep in sync with how cmake_do_configure() invokes cmake
    echo $(which cmake) \
      ${OECMAKE_SITEFILE} \
      ${OECMAKE_SOURCEPATH} \
      -DCMAKE_INSTALL_PREFIX:PATH=${prefix} \
      -DCMAKE_INSTALL_SO_NO_EXE=0 \
      -DCMAKE_TOOLCHAIN_FILE=${WORKDIR}/toolchain.cmake \
      -DCMAKE_VERBOSE_MAKEFILE=1 \
      ${EXTRA_OECMAKE} \
      -Wno-dev > ${WORKDIR}/cmake.status
}

# We set OECMAKE_BUILDPATH to be different from S above, so there's no need to
# test at run time.
do_clean_append() {
    buildpath = bb.data.getVar('OECMAKE_BUILDPATH', d, 1)
    if buildpath and os.path.exists(buildpath):
        bb.note('Removing ' + buildpath)
        os.system('rm -rf ' + buildpath)
}
