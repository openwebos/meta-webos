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
KERNEL_IMAGE_BASE_NAME = "${KERNEL_IMAGETYPE}-${PV}-${PR}-${MACHINE}-${WEBOS_IMAGE_NAME_SUFFIX}"
# Don't rebuild the kernel (or the rootfs) images if the only thing that's changed
# is the start time of the build or the build number.
BB_HASHBASE_WHITELIST_append = " WEBOS_IMAGE_NAME_SUFFIX"

# Add WEBOS_DISTRO_RELEASE_CODENAME and WEBOS_DISTRO_BUILD_ID to what's displayed
# as the "build configuration".
BUILDCFG_VARS += "WEBOS_DISTRO_RELEASE_CODENAME WEBOS_DISTRO_BUILD_ID"


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

OPKGLIBDIR_forcevariable := "${libdir}"

INHERIT_DISTRO ?= "debian devshell sstate license webos_base"

# Remove version-going-backwards from ERROR_QA when oe-core is upgraded to include:
# http://lists.openembedded.org/pipermail/openembedded-core/2013-August/082351.html
WARN_QA += "version-going-backwards"

QEMU_TARGETS ?= "arm i386"

# Default WEBOS_TARGET_* values (see webos_cmake.bbclass)
WEBOS_TARGET_CORE_OS ?= "rockhopper"
WEBOS_TARGET_MACHINE_IMPL ?= "emulator"


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
