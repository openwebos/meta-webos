# (c) Copyright 2012  Hewlett-Packard Development Company, L.P. 

DESCRIPTION = "Reference Open WebOS image."
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

# IMAGE_FEATURES control content of the webos reference images
# 
# By default we install task-webos-boot and core and extended packages - this gives full
# working Open WebOS image.
#
# Available IMAGE_FEATURES:
#
# - webos-test          - QA
# - webos-temp          - experimental packages
# - tools-sdk           - SDK
# - tools-debug         - debugging tools
# - tools-profile       - profiling tools
# - tools-testapps      - tools usable to make some device tests
# - nfs-server          - NFS server (exports / over NFS to everybody)
# - ssh-server-dropbear - SSH server (dropbear)
# - ssh-server-openssh  - SSH server (openssh)
# - debug-tweaks        - makes an image suitable for development
# - webos-internal      - Internal use only
#

PACKAGE_GROUP_webos-boot = "task-webos-boot"
PACKAGE_GROUP_webos-core = "task-webos-core"
PACKAGE_GROUP_webos-extended = "task-webos-extended"
PACKAGE_GROUP_webos-internal = "task-webos-internal"
PACKAGE_GROUP_webos-test = "task-webos-test"
PACKAGE_GROUP_webos-temp = "task-webos-temp"
PACKAGE_GROUP_tools-debug = "task-core-tools-debug"
PACKAGE_GROUP_tools-profile = "task-core-tools-profile"
PACKAGE_GROUP_tools-testapps = "task-core-tools-testapps"
PACKAGE_GROUP_tools-sdk = "task-core-sdk task-core-standalone-sdk-target"
PACKAGE_GROUP_nfs-server = "task-core-nfs-server"
PACKAGE_GROUP_ssh-server-dropbear = "task-core-ssh-dropbear"
PACKAGE_GROUP_ssh-server-openssh = "task-core-ssh-openssh"
PACKAGE_GROUP_package-management = "${ROOTFS_PKGMANAGE}"

WEBOS_IMAGE_BASE_INSTALL = '\
    task-webos-boot \
    task-webos-core \
    task-webos-extended \
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

