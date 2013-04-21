# Copyright (c) 2012-2013 Hewlett-Packard Development Company, L.P.
# Copyright (c) 2013 LG Electronics, Inc.

require conf/distro/include/webos-filesystem-paths.inc

require conf/distro/include/webos-component-head.inc
require conf/distro/include/webos-preferred-providers.inc
require conf/distro/include/webos-preferred-versions.inc
require conf/distro/include/webos-features.inc
require conf/distro/include/webos-toolchain.inc
require conf/distro/include/webos-sdk.inc
require conf/distro/include/webos-repositories.inc

EXTRAOPKGCONFIG = ""

WEBOS_DISTRO_RELEASE_CODENAME = "${DISTRO}-${@base_get_metadata_git_branch('.', None).strip()}"

# All of the types of official build jobs should override this in the following format:
#  "[prefix-]<build-number>".  The optional [prefix-] identifies different build jobs,
# for example engineering builds; it is left off for the official build.
WEBOS_DISTRO_BUILD_ID ?= "unofficial"

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

# opkglibdir is used in rootfs-ipk, change that to
# OPKGLIBDIR_webos := "${libdir}"
# and remove opkg*bbappends when oe-core is upgraded to revision which includes this:
# http://lists.linuxtogo.org/pipermail/openembedded-core/2013-February/035407.html 
opkglibdir_forcevariable = "${libdir}/opkg"

INHERIT_DISTRO ?= "debian devshell sstate license webos_base"

# Drop the unsafe-references-in-binaries and unsafe-references-in-scripts that
# appear in the default WARN_QA because we don't plan to support a separate
# partition for /usr. Once oe-core is upgraded to include
# 2fb58da56e8e7044de21fd10fe9164d204587236, remove this assignment to enable the
# xorg-driver-abi and textrel checks.
WARN_QA = "ldflags useless-rpaths rpaths staticdev libdir"

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
            Ubuntu 12.04.1 LTS \n \
            Ubuntu 12.04.2 LTS \n \
            Ubuntu 12.10 \n \
            "
