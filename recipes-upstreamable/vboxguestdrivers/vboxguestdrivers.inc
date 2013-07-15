# Copyright (c) 2013 LG Electronics, Inc.

SUMMARY = "VirtualBox Linux Guest Drivers"
SECTION = "core"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://${WORKDIR}/${VBOX_NAME}/COPYING;md5=e197d5641bb35b29d46ca8c4bf7f2660"

DEPENDS = "virtual/kernel"

INC_PR = "r3"

inherit module kernel-module-split

COMPATIBLE_MACHINE = "(qemux86|qemux86-64)"

S = "${WORKDIR}/vbox_module"

export BUILD_TARGET_ARCH="${ARCH}"
export KERN_DIR="${STAGING_KERNEL_DIR}"

addtask export_sources before do_patch after do_unpack

do_export_sources() {
    mkdir -p "${S}"
    ${WORKDIR}/${VBOX_NAME}/src/VBox/Additions/linux/export_modules ${T}/vbox_modules.tar.gz
    tar -C "${S}" -xzf ${T}/vbox_modules.tar.gz

    #add a mount utility to use shared folder from VBox Addition Source Code
    mkdir -p "${S}/utils"
    install ${WORKDIR}/${VBOX_NAME}/src/VBox/Additions/linux/sharedfolders/mount.vboxsf.c ${S}/utils
    install ${WORKDIR}/${VBOX_NAME}/src/VBox/Additions/linux/sharedfolders/vbsfmount.c ${S}/utils
    install ${S}/../Makefile.utils ${S}/utils/Makefile

}

#add process to compile and install mount utility
do_compile_append() {
    oe_runmake -C ${S}/utils
}

module_do_install() {
    MODULE_DIR=${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/misc
    install -d $MODULE_DIR
    install -m 644 vboxguest.ko $MODULE_DIR
    install -m 644 vboxsf.ko $MODULE_DIR
    install -m 644 vboxvideo.ko $MODULE_DIR
}

do_install_append() {
    install -d ${D}${base_sbindir}
    install -m 755 ${S}/utils/mount.vboxsf ${D}${base_sbindir}
}

PACKAGES += "kernel-module-vboxguest kernel-module-vboxsf kernel-module-vboxvideo"
RDEPENDS_${PN} += "kernel-module-vboxguest kernel-module-vboxsf kernel-module-vboxvideo"

FILES_${PN} = "${base_sbindir}"
ALLOW_EMPTY_${PN} = "1"

# autoload if installed
module_autoload_vboxguest = "vboxguest"
module_autoload_vboxsf = "vboxsf"
module_autoload_vboxvideo = "vboxvideo"
