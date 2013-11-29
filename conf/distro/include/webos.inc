# Copyright (c) 2012-2013 LG Electronics, Inc.

require conf/distro/include/webos-filesystem-paths.inc

require conf/distro/include/webos-component-head.inc
require conf/distro/include/webos-preferred-providers.inc
require conf/distro/include/webos-recipe-blacklist.inc
require conf/distro/include/webos-preferred-versions.inc
require conf/distro/include/webos-features.inc
require conf/distro/include/webos-toolchain.inc
require conf/distro/include/webos-sdk.inc
require conf/distro/include/webos-repositories.inc

EXTRAOPKGCONFIG = ""

WEBOS_DISTRO_RELEASE_CODENAME = "${DISTRO}-${@base_get_metadata_git_branch('.', None).strip()}"
WEBOS_DISTRO_PRERELEASE ?= "devel"

# All of the types of official build jobs should override this in the following
# format: "[site:][prefix-]<build-number>", where the optional [site:] identifies
# the build server site, and the optional [prefix-] identifies different build
# jobs, for example engineering builds; it is left off for the official build.
# [site>:] is dropped for the authoritative "official" build.
WEBOS_DISTRO_BUILD_ID ?= "unofficial"

# Only suffix image names with DATETIME when the build is unofficial; otherwise,
# use WEBOS_DISTRO_BUILD_ID.
WEBOS_IMAGE_NAME_SUFFIX = "${@ base_conditional( \
                                  'WEBOS_DISTRO_BUILD_ID', 'unofficial', \
                                  '${DATETIME}', '${WEBOS_DISTRO_BUILD_ID}', d) }"
IMAGE_NAME = "${IMAGE_BASENAME}-${MACHINE}-${WEBOS_IMAGE_NAME_SUFFIX}"
KERNEL_IMAGE_BASE_NAME = "${KERNEL_IMAGETYPE}-${PKGE}-${PKGV}-${PKGR}-${MACHINE}-${WEBOS_IMAGE_NAME_SUFFIX}"
MODULE_IMAGE_BASE_NAME = "modules-${PKGE}-${PKGV}-${PKGR}-${MACHINE}-${WEBOS_IMAGE_NAME_SUFFIX}"
# Don't rebuild the kernel (or the rootfs) images if the only thing that's changed
# is the start time of the build or the build number.
BB_HASHBASE_WHITELIST_append = " WEBOS_IMAGE_NAME_SUFFIX"

# Add WEBOS_DISTRO_RELEASE_CODENAME and WEBOS_DISTRO_BUILD_ID to what's displayed
# as the "build configuration".
BUILDCFG_VARS += "WEBOS_DISTRO_RELEASE_CODENAME WEBOS_DISTRO_BUILD_ID"

# Apply fix from oe-core commit 89d7d46947d9bb8c7bf568c65e52d5bbe159027f
# Drop this when upgrading oe-core to 1.6 release or newer
DEPENDS_pn-cracklib-native_class-native = "zlib-native"

# Drop virtual/kernel build-time dependency
# Drop this when upgrading oe-core to 1.6 release or newer with this change:
# http://lists.openembedded.org/pipermail/openembedded-core/2013-November/086514.html
DEPENDS_pn-packagegroup-core-boot = ""

# Build time dependency on TUNE_PKGARCH recipes base-passwd, shadow, shadow-sysroot
# Drop this when upgrading oe-core to 1.6 release or newer with this change:
# http://lists.openembedded.org/pipermail/openembedded-core/2013-November/085849.html
PACKAGE_ARCH_pn-xuser-account = "${TUNE_PKGARCH}"

# Drop these PACKAGE_ARCH assignments when upgrading oe-core to 1.6 release or newer with this change:
# http://lists.openembedded.org/pipermail/openembedded-core/2013-November/085999.html
# change from allarch, because drobear has different signature for each TUNE_PKGARCH
PACKAGE_ARCH_pn-packagegroup-core-ssh-dropbear = "${TUNE_PKGARCH}"
# change from allarch, because fontconfig has different signature for each TUNE_PKGARCH
PACKAGE_ARCH_pn-packagegroup-fonts-truetype = "${TUNE_PKGARCH}"
# depends on eglibc-locale, libgcc, gcc-runtime, eglibc or external-toochain based on MACHINE configuration
PACKAGE_ARCH_pn-packagegroup-core-standalone-sdk-target = "${MACHINE_ARCH}"
PACKAGE_ARCH_pn-packagegroup-cli-tools = "${MACHINE_ARCH}"
PACKAGE_ARCH_pn-packagegroup-core-buildessential = "${MACHINE_ARCH}"
PACKAGE_ARCH_pn-packagegroup-core-nfs = "${MACHINE_ARCH}"
PACKAGE_ARCH_pn-packagegroup-core-sdk = "${MACHINE_ARCH}"
PACKAGE_ARCH_pn-packagegroup-sdk-target = "${MACHINE_ARCH}"
PACKAGE_ARCH_pn-nfs-export-root = "${MACHINE_ARCH}"

# Override these in webOS based distros to modify DISTRO_EXTRA_R*
WEBOS_DEFAULT_EXTRA_RDEPENDS = ""
WEBOS_DEFAULT_EXTRA_RRECOMMENDS = ""

DISTRO_EXTRA_RDEPENDS += " ${WEBOS_DEFAULT_EXTRA_RDEPENDS}"
DISTRO_EXTRA_RRECOMMENDS += " ${WEBOS_DEFAULT_EXTRA_RRECOMMENDS}"

WEBOSQEMUDEPS = "${@base_contains("INCOMPATIBLE_LICENSE", "GPLv3", "", "qemu-config",d)}"
DISTRO_EXTRA_RDEPENDS_append_qemuarm = " ${WEBOSQEMUDEPS}"
DISTRO_EXTRA_RDEPENDS_append_qemux86 = " ${WEBOSQEMUDEPS}"

CACHE = "${TMPDIR}/cache/${TCMODE}-${TCLIBC}${@['', '/' + str(d.getVar('MACHINE', True))][bool(d.getVar('MACHINE', True))]}${@['', '/' + str(d.getVar('SDKMACHINE', True))][bool(d.getVar('SDKMACHINE', True))]}"

USER_CLASSES ?= ""

# WEBOS_EXTRA_MACHINEOVERRIDES will appear in OVERRIDES before what's added by
# the machine architecture .inc file, which appears before MACHINE.
# WEBOS_EXTRA_MACHINEOVERRIDES is set in webos_machine_impl_dep.bbclass . Do it
# this way instead of just in the bbclass so that the position in OVERRIDES is
# fixed.
WEBOS_EXTRA_MACHINEOVERRIDES ?= ""
MACHINEOVERRIDES =. "${WEBOS_EXTRA_MACHINEOVERRIDES}"
MACHINEOVERRIDES[vardepsexclude] += "WEBOS_EXTRA_MACHINEOVERRIDES"
OVERRIDES[vardepsexclude] += "WEBOS_EXTRA_MACHINEOVERRIDES"

OPKGLIBDIR_forcevariable := "${libdir}"

INHERIT_DISTRO ?= "debian devshell sstate license webos_base buildstats-summary"

# Remove version-going-backwards from ERROR_QA when oe-core is upgraded to include:
# http://lists.openembedded.org/pipermail/openembedded-core/2013-August/082351.html
WARN_QA_append = " version-going-backwards webos-enh-sub-warning"
ERROR_QA_append = " webos-enh-sub-error"

QEMU_TARGETS ?= "arm i386"

# Default WEBOS_TARGET_* values (see webos_cmake.bbclass)
WEBOS_TARGET_CORE_OS ?= "rockhopper"
WEBOS_TARGET_MACHINE_IMPL ?= "emulator"

# Change default PACKAGE_ARCH to MACHINE_ARCH, one of important goals for OE
# is to share binary feed and sstate between MACHINEs with the same architecture
# design of some webOS components doesn't support this very well - TUNE_PKGARCH
# recipes (runtime) depending on MACHINE_ARCH recipes, couple of recipes
# could be TUNE_PKGARCH if the MACHINE-specific changes are moved to separate
# config file provided by small MACHINE_ARCH recipe instead of using MACHINE
# variable in build-time to customize component.
# All officially supported MACHINEs have different architecture, so we weren't
# sharing any packages/sstate.
# Be aware that this wasn't tested with nativesdk.
PACKAGE_ARCH = "${MACHINE_ARCH}"

# For toolchain recipes keep TUNE_PKGARCH, to support default STAGING_BINDIR_TOOLCHAIN
# as defined in bitbake.conf
# STAGING_BINDIR_TOOLCHAIN = "${STAGING_DIR_NATIVE}${bindir_native}/${TUNE_PKGARCH}${TARGET_VENDOR}-${TARGET_OS}"
PACKAGE_ARCH_pn-binutils-cross = "${TUNE_PKGARCH}"
PACKAGE_ARCH_pn-gcc-cross = "${TUNE_PKGARCH}"
PACKAGE_ARCH_pn-gcc-cross-initial = "${TUNE_PKGARCH}"
# libgcc.inc: target=`echo ${MULTIMACH_TARGET_SYS} | sed -e s#-${SDKPKGSUFFIX}##`
PACKAGE_ARCH_pn-libgcc = "${TUNE_PKGARCH}"
# gcc-runtime.inc: mtarget=`echo ${MULTIMACH_TARGET_SYS} | sed -e s#-${SDKPKGSUFFIX}##`
PACKAGE_ARCH_pn-gcc-runtime = "${TUNE_PKGARCH}"

# Drop this when upgrading oe-core to 1.6 release or newer, with this fix:
# http://lists.openembedded.org/pipermail/openembedded-core/2013-November/087115.html
# Assign vardepvalue, because otherwise signature is changed before and after
# perl is built (from None to real version in config.sh).
get_perl_version[vardepvalue] = "${PERL_OWN_DIR}"

# The Open webOS distro is packaged into these image types:
IMAGE_FSTYPES ?= "tar.gz"

# Only create a vmdk image when we're building for x86 emulator,
# i.e. when MACHINE is qemux86 or qemux86-64
# If IMAGE_FSTYPES_append_qemux86 = " vmdk" is used, the inherit of
# image-vmdk.bbclass in image.bbclass doesn't happen, because IMAGE_FSTYPES
# overrides are expanded too late.
IMAGE_FSTYPES := "${@ '${IMAGE_FSTYPES} vmdk' if '${MACHINE}'.startswith('qemux86') else '${IMAGE_FSTYPES}' }"

# "tar.bz2 ext3" is added by qemu.inc, replace tar.bz2 with tar.gz in it
IMAGE_FSTYPES := "${@ ' ${IMAGE_FSTYPES} '.replace(' tar.bz2 ',' tar.gz ') }"

PATH_prepend = "${WEBOS_EXTRA_PATH}"

# Ensure that git checkout files have the same permissions when do_unpack
# is executed on builders with different umask.
# Drop this when upgrading oe-core to 1.6 release or newer, with this fix:
# http://lists.openembedded.org/pipermail/openembedded-core/2013-November/086881.html
# For details see:
# https://bugzilla.yoctoproject.org/show_bug.cgi?id=5590
do_unpack[umask] = "022"

# Redirect console output to keep it from spewing all over the VirtualBox window
# (This is added to qemux86 kernel cmdline by openembedded-core/meta/classes/syslinux.bbclass)
APPEND_append_qemux86 = " console=ttyS0"

MIRRORS =+ "\
ftp://.*/.*      http://downloads.yoctoproject.org/mirror/sources/ \n \
http://.*/.*     http://downloads.yoctoproject.org/mirror/sources/ \n \
https://.*/.*    http://downloads.yoctoproject.org/mirror/sources/ \n"

# The CONNECTIVITY_CHECK_URI's are used to test whether we can succesfully
# fetch from the network (and warn you if not). To disable the test set
# the variable to be empty.
CONNECTIVITY_CHECK_URIS ?= "git://git.yoctoproject.org/yocto-firewall-test;protocol=git;rev=HEAD \
                          https://eula-downloads.yoctoproject.org/index.php \
                          http://bugzilla.yoctoproject.org/report.cgi"

# TODO can check on all these host machines.
SANITY_TESTED_DISTROS ?= " \
            Ubuntu-12.04 \n \
            Ubuntu-12.10 \n \
            "
