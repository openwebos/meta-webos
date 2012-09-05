# (c) Copyright 2012  Hewlett-Packard Development Company, L.P. 

DESCRIPTION = "Reference Open webOS image"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

# IMAGE_FEATURES control content of the webos reference images
# 
# By default we install packagegroup-webos-boot and -extended packages - this gives 
# full working Open webOS image.
#
# Available IMAGE_FEATURES:
#
# - webos-test          - QA
# - webos-temp          - temporary packages
# - tools-sdk           - SDK
# - tools-debug         - debugging tools
# - tools-profile       - profiling tools
# - tools-testapps      - tools usable to make some device tests
# - nfs-server          - NFS server (exports / over NFS to everybody)
# - ssh-server-dropbear - SSH server (dropbear)
# - ssh-server-openssh  - SSH server (openssh)
# - debug-tweaks        - makes an image suitable for development
#

PACKAGE_GROUP_webos-boot = "packagegroup-webos-boot"
PACKAGE_GROUP_webos-extended = "packagegroup-webos-extended"
PACKAGE_GROUP_webos-test = "packagegroup-webos-test"
PACKAGE_GROUP_webos-temp = "packagegroup-webos-temp"
PACKAGE_GROUP_tools-debug = "packagegroup-core-tools-debug"
PACKAGE_GROUP_tools-profile = "packagegroup-core-tools-profile"
PACKAGE_GROUP_tools-testapps = "packagegroup-core-tools-testapps"
PACKAGE_GROUP_tools-sdk = "packagegroup-core-sdk packagegroup-core-standalone-sdk-target"
PACKAGE_GROUP_nfs-server = "packagegroup-core-nfs-server"
PACKAGE_GROUP_ssh-server-dropbear = "packagegroup-core-ssh-dropbear"
PACKAGE_GROUP_ssh-server-openssh = "packagegroup-core-ssh-openssh"
PACKAGE_GROUP_package-management = "${ROOTFS_PKGMANAGE}"

# IMAGE_FEATURES_REPLACES_foo = 'bar1 bar2'
# Including image feature foo would replace the image features bar1 and bar2
IMAGE_FEATURES_REPLACES_ssh-server-openssh = "ssh-server-dropbear"

# IMAGE_FEATURES_CONFLICTS_foo = 'bar1 bar2'
# An error exception would be raised if both image features foo and bar1(or bar2) are included

WEBOS_IMAGE_DEFAULT_FEATURES = "webos-temp ssh-server-dropbear"

WEBOS_IMAGE_BASE_INSTALL = '\
    packagegroup-webos-boot \
    packagegroup-webos-extended \
    \
    ${WEBOS_IMAGE_EXTRA_INSTALL} \
    '

WEBOS_IMAGE_EXTRA_INSTALL ?= ""

IMAGE_INSTALL ?= "${WEBOS_IMAGE_BASE_INSTALL}"

inherit image

# Create /etc/timestamp during image construction to give a reasonably sane default time setting
ROOTFS_POSTPROCESS_COMMAND += "rootfs_update_timestamp ; "

# Zap the root password if debug-tweaks feature is not enabled
ROOTFS_POSTPROCESS_COMMAND += '${@base_contains("IMAGE_FEATURES", "debug-tweaks", "", "zap_root_password ; ",d)}'
# Allow openssh accept empty password login if both debug-tweaks and ssh-server-openssh are enabled
ROOTFS_POSTPROCESS_COMMAND += '${@base_contains("IMAGE_FEATURES", "debug-tweaks ssh-server-openssh", "openssh_allow_empty_password; ", "",d)}'

# XXX Workaround for bug in openembedded-core. Remove once we re-pin to a commit of openembedded-core
# that includes http://cgit.openembedded.org/openembedded-core/commit/?id=28715eff6dff3415b1d7b0be8cbb465c417e307f
build_boot_dd_prepend () {
    # Create a symlink from where boot_dd_append() expects to find the kernel to where it actually is
    mkdir -p ${STAGING_DIR_HOST}/kernel
    ln -snf ${STAGING_KERNEL_DIR}/bzImage ${STAGING_DIR_HOST}/kernel
}

build_boot_dd_append () {
    # Remove the symlink and the directory so there's no trace of our workaround
    rm -f ${STAGING_DIR_HOST}/kernel/bzImage
    rmdir --ignore-fail-on-non-empty ${STAGING_DIR_HOST}/kernel
}

