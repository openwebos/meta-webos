# Copyright (c) 2012-2013 LG Electronics, Inc.

DESCRIPTION = "Reference Open webOS image"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

# IMAGE_FEATURES control content of the webOS reference images
#
# By default we install packagegroup-core-boot and packagegroup-webos-extended packages
# this gives full working Open webOS image.
#
# Available IMAGE_FEATURES:
#
# - webos-test          - QA
# - webos-temp          - temporary packages
# - webos-extended      - webOS components
#
# and IMAGE_FEATURES from core-image

PACKAGE_GROUP_webos-extended = "packagegroup-webos-extended"
PACKAGE_GROUP_webos-test = "packagegroup-webos-test"
PACKAGE_GROUP_webos-temp = "packagegroup-webos-temp"

WEBOS_IMAGE_DEFAULT_FEATURES = "webos-temp ssh-server-dropbear package-management"

WEBOS_IMAGE_BASE_INSTALL = '\
    packagegroup-core-boot \
    packagegroup-webos-extended \
    \
    ${WEBOS_IMAGE_EXTRA_INSTALL} \
    '

WEBOS_IMAGE_EXTRA_INSTALL ?= ""

IMAGE_INSTALL ?= "${WEBOS_IMAGE_BASE_INSTALL}"

# Add ${webos_sysconfdir}/build/image-name during image construction that contains the image name
ROOTFS_POSTPROCESS_COMMAND += "rootfs_set_image_name ; clean_python_installation ; "

# Can be used to echo image name to ${webos_sysconfdir}/build/image-name
rootfs_set_image_name () {
    mkdir -p ${IMAGE_ROOTFS}${webos_sysconfdir}/build
    echo ${IMAGE_BASENAME} > ${IMAGE_ROOTFS}${webos_sysconfdir}/build/image-name
}

# cleanup python installation
clean_python_installation () {
    for p in `find ${IMAGE_ROOTFS}${libdir} -name "*pyo" `
    do
        rm -f $p
    done
}

# A hook function to support read-only-rootfs IMAGE_FEATURES
webos_read_only_rootfs_hook () {
    # Tweak the mount option and fs_passno for rootfs in fstab
    sed -i -e '/^[#[:space:]]*\/dev\/root/{s/rw/ro/;s/\([[:space:]]*[[:digit:]]\)\([[:space:]]*\)[[:digit:]]$/\1\20/}' ${IMAGE_ROOTFS}/etc/fstab

    # Change the value of ROOTFS_READ_ONLY in /etc/default/rcS to yes
    if [ -e ${IMAGE_ROOTFS}/etc/default/rcS ]; then
         sed -i 's/ROOTFS_READ_ONLY=no/ROOTFS_READ_ONLY=yes/' ${IMAGE_ROOTFS}/etc/default/rcS
    fi

    # Change the value of ROOTFS_READ_ONLY in /etc/init/SetInitEnv.conf to yes
    if [ -e ${IMAGE_ROOTFS}/etc/init/SetInitEnv.conf ]; then
         sed -i 's/ROOTFS_READ_ONLY="*no"*/ROOTFS_READ_ONLY=yes/' ${IMAGE_ROOTFS}/etc/init/SetInitEnv.conf
    fi

    # If both /etc/default/rcS and /etc/init/SetInitEnv.conf are not found,
    # then ROOTFS_READ_ONLY will not be properly passed to the init process,
    # and therefore this class file must be modified to remedy that issue.
    if [ ! -e ${IMAGE_ROOTFS}/etc/default/rcS -a ! -e ${IMAGE_ROOTFS}/etc/init/SetInitEnv.conf ]; then
         bberror "Both ${IMAGE_ROOTFS}/etc/default/rcS and ${IMAGE_ROOTFS}/etc/init/SetInitEnv.conf are not found -- unable to correct the ROOTFS_READ_ONLY environment variable."
         exit 1
    fi
}
ROOTFS_POSTPROCESS_COMMAND += '${@base_contains("IMAGE_FEATURES", "read-only-rootfs", "webos_read_only_rootfs_hook ; ", "", d)}'

# Luna-service services should be executable only by user and group,
# because it'd be possible to hijack them with LD_PRELOAD and
# fool the hub daemon to raise own LS2-security permissions.
luna_service2_check_permissions () {
    # Look for exeName in LS2 roles files, extract paths to service binaries
    dirs='${IMAGE_ROOTFS}${webos_sysbus_prvrolesdir} ${IMAGE_ROOTFS}${webos_sysbus_pubrolesdir}'
    services=`find $dirs -name '*.json' | \
              xargs grep exeName | \
              sed -n -r 's/^.*"exeName"\s*:\s*"([^"]+)".*$/\1/p' | \
              sort | uniq`
    for f in $services
    do
        # js is a special service
        if [ "$f" = 'js' ] ; then
            continue
        fi
        # luna-send-pub and ls-monitor-pub should be executable by everybody
        if [ "$f" = '${bindir}/luna-send-pub' -o "$f" = '${bindir}/ls-monitor-pub' ] ; then
            continue
        fi

        # Check file permissions of the file. We want that the file ins't
        # accessible by others:
        # -rwxr-x--- (0750)
        if ! perms=`stat -L -c %a ${IMAGE_ROOTFS}$f` 2>/dev/null ; then
            bbwarn "QA Issue: Unable to check the binary $f mentioned in LS2 role files"
            continue
        fi
        # Get the "other" part of octal permissions
        world_bits=`echo $perms | cut -c 3-`
        if [ $world_bits != 0 ]; then
            bbwarn "QA Issue: LS2 service $f is accessible for the whole world"
        fi
    done
}
# Since many components restrict permissions only for the real hardware,
# the permissions of luna services should be checked only for the relevant target.
ROOTFS_POSTPROCESS_COMMAND += '${@base_conditional("WEBOS_TARGET_MACHINE_IMPL", "hardware", "luna_service2_check_permissions ; ", "", d)}'

inherit core-image

