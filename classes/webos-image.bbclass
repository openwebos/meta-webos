# (c) Copyright 2012  Hewlett-Packard Development Company, L.P. 

DESCRIPTION = "Reference Open WebOS image."
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

# IMAGE_FEATURES control content of the webos reference images
# 
# By default we install task-core-boot and task-base packages - this gives us
# working (console only) rootfs.
#
# Available IMAGE_FEATURES:
#
# - apps-console-core
# - x11-mini            - minimal environment for X11 server 
# - x11-base            - X11 server + minimal desktop	
# - x11-sato            - OpenedHand Sato environment
# - x11-netbook         - Metacity based environment for netbooks
# - apps-x11-core       - X Terminal, file manager, file editor
# - apps-x11-games
# - apps-x11-pimlico    - OpenedHand Pimlico apps
# - tools-sdk           - SDK
# - tools-debug         - debugging tools
# - tools-profile       - profiling tools
# - tools-testapps      - tools usable to make some device tests
# - nfs-server          - NFS server (exports / over NFS to everybody)
# - ssh-server-dropbear - SSH server (dropbear)
# - ssh-server-openssh  - SSH server (openssh)
# - debug-tweaks        - makes an image suitable for development
#
PACKAGE_GROUP_apps-console-core = "task-core-apps-console"

PACKAGE_GROUP_webos-core = "task-webos-core"
PACKAGE_GROUP_webos-extended = "task-webos-extended"
PACKAGE_GROUP_webos-internal = "task-webos-internal"
PACKAGE_GROUP_webos-custom = "task-webos-custom"
PACKAGE_GROUP_webos-engineering = "task-webos-engineering"
PACKAGE_GROUP_webos-fulcrum = "task-webos-fulcrum"
PACKAGE_GROUP_webos-rockhopper = "task-webos-rockhopper"
PACKAGE_GROUP_webos-terra = "task-webos-terra"
PACKAGE_GROUP_webos-test = "task-webos-test"

PACKAGE_GROUP_tools-debug = "task-core-tools-debug"
PACKAGE_GROUP_tools-profile = "task-core-tools-profile"
PACKAGE_GROUP_tools-testapps = "task-core-tools-testapps"
PACKAGE_GROUP_tools-sdk = "task-core-sdk task-core-standalone-sdk-target"
PACKAGE_GROUP_nfs-server = "task-core-nfs-server"
PACKAGE_GROUP_ssh-server-dropbear = "task-core-ssh-dropbear"
PACKAGE_GROUP_ssh-server-openssh = "task-core-ssh-openssh"
PACKAGE_GROUP_package-management = "${ROOTFS_PKGMANAGE}"
PACKAGE_GROUP_qt4-pkgs = "task-core-qt-demos"

IMAGE_FEATURES_REPLACES_ssh-server-openssh = "ssh-server-dropbear"

WEBOS_IMAGE_BASE_INSTALL = '\
    task-core-boot \
    \
    ${WEBOS_IMAGE_EXTRA_INSTALL} \
    '
#     task-base \

WEBOS_IMAGE_EXTRA_INSTALL ?= ""

IMAGE_INSTALL ?= "${WEBOS_IMAGE_BASE_INSTALL}"

X11_IMAGE_FEATURES  = "x11-base apps-x11-core package-management"
ENHANCED_IMAGE_FEATURES = "${X11_IMAGE_FEATURES} apps-x11-games apps-x11-pimlico package-management"
SATO_IMAGE_FEATURES = "${ENHANCED_IMAGE_FEATURES} x11-sato ssh-server-dropbear"

inherit image

# Create /etc/timestamp during image construction to give a reasonably sane default time setting
ROOTFS_POSTPROCESS_COMMAND += "rootfs_update_timestamp ; "

# Zap the root password if debug-tweaks feature is not enabled
ROOTFS_POSTPROCESS_COMMAND += '${@base_contains("IMAGE_FEATURES", "debug-tweaks", "", "zap_root_password ; ",d)}'
# Allow openssh accept empty password login if both debug-tweaks and ssh-server-openssh are enabled
ROOTFS_POSTPROCESS_COMMAND += '${@base_contains("IMAGE_FEATURES", "debug-tweaks ssh-server-openssh", "openssh_allow_empty_password; ", "",d)}'

