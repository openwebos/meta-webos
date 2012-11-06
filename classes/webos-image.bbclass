# (c) Copyright 2012-2013 Hewlett-Packard Development Company, L.P.
# (c) Copyright 2013 LG Electronics

DESCRIPTION = "Reference Open webOS image"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

# IMAGE_FEATURES control content of the webOS reference images
#
# By default we install packagegroup-webos-boot and -extended packages - this gives
# full working Open webOS image.
#
# Available IMAGE_FEATURES:
#
# - webos-test          - QA
# - webos-temp          - temporary packages
# - webos-extended      - webOS components
#
# and IMAGE_FEATURES from core-image

PACKAGE_GROUP_webos-boot = "packagegroup-webos-boot"
PACKAGE_GROUP_webos-extended = "packagegroup-webos-extended"
PACKAGE_GROUP_webos-test = "packagegroup-webos-test"
PACKAGE_GROUP_webos-temp = "packagegroup-webos-temp"

WEBOS_IMAGE_DEFAULT_FEATURES = "webos-temp ssh-server-dropbear package-management"

WEBOS_IMAGE_BASE_INSTALL = '\
    packagegroup-webos-boot \
    packagegroup-webos-extended \
    \
    ${WEBOS_IMAGE_EXTRA_INSTALL} \
    '

WEBOS_IMAGE_EXTRA_INSTALL ?= ""

IMAGE_INSTALL ?= "${WEBOS_IMAGE_BASE_INSTALL}"

# Add ${webos_sysconfdir}/build/image-name during image construction that contains the image name
ROOTFS_POSTPROCESS_COMMAND += "rootfs_set_image_name ; "

# Can be used to echo image name to ${webos_sysconfdir}/build/image-name
rootfs_set_image_name () {
    mkdir -p ${IMAGE_ROOTFS}${webos_sysconfdir}/build
    echo ${IMAGE_BASENAME} > ${IMAGE_ROOTFS}${webos_sysconfdir}/build/image-name
}

inherit core-image
