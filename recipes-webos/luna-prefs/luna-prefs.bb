# Copyright (c) 2012-2013 LG Electronics, Inc.

SUMMARY = "Open webOS preferences manager"
SECTION = "webos/base"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS = "luna-service2 cjson sqlite3 glib-2.0 nyx-lib"

PR = "r10"

#inherit webos_component
inherit webos_public_repo
inherit webos_enhanced_submissions
inherit webos_cmake
inherit webos_program
inherit webos_library
inherit webos_system_bus
inherit webos_machine_dep

WEBOS_GIT_PARAM_TAG = "submissions/${WEBOS_SUBMISSION}"
SRC_URI = "${OPENWEBOS_GIT_REPO_COMPLETE}"
S = "${WORKDIR}/git"

PRODUCT_DEVICE_NAME ?= "Open webOS Device"
PRODUCT_DEVICE_NAME_BRANDED ?= "LGE Open webOS Device"
PRODUCT_DEVICE_NAME_SHORT ?= "webOS Device"
PRODUCT_DEVICE_NAME_SHORT_BRANDED ?= "LGE Open webOS Device"
PRODUCT_DEVICE_NAME_PRODUCT_LINE_NAME ?= "Open webOS Device"
PRODUCT_DEVICE_NAME_PRODUCT_CLASS ?= "Tablet"
PRODUCT_DEVICE_NAME_PRODUCT_BROWSER_OS_NAME ?= "Open webOS"

# Versions can not be overridden
PRODUCT_DEVICE_NAME_PRODUCT_LINE_VERSION = "${WEBOS_DISTRO_API_VERSION}"
PRODUCT_DEVICE_NAME_PRODUCT_BROWSER_OS_VERSION = "${WEBOS_DISTRO_API_VERSION}"

do_install_append() {
    # CFISH-930: remove "other" perms granted by pmmakefiles (aka palmmake):
    chmod o-rwx ${D}${bindir}/luna-prefs-service
    chmod o-rwx ${D}${bindir}/lunaprop

    install -d ${D}${sysconfdir}/prefs/properties

    # Let's not require a submission process to add to the whitelist
    cat > ${D}${sysconfdir}/prefs/public_properties <<EOF
com.palm.properties.nduid
EOF
    chmod 644 ${D}${sysconfdir}/prefs/public_properties

    echo -n "${PRODUCT_DEVICE_NAME}"               > ${D}${sysconfdir}/prefs/properties/deviceName
    echo -n "${PRODUCT_DEVICE_NAME_BRANDED}"       > ${D}${sysconfdir}/prefs/properties/deviceNameBranded
    echo -n "${PRODUCT_DEVICE_NAME_SHORT}"         > ${D}${sysconfdir}/prefs/properties/deviceNameShort
    echo -n "${PRODUCT_DEVICE_NAME_SHORT_BRANDED}" > ${D}${sysconfdir}/prefs/properties/deviceNameShortBranded
    echo -n ${MACHINE} > ${D}${sysconfdir}/prefs/properties/machineName
    echo -n ${PRODUCT_DEVICE_NAME_PRODUCT_LINE_NAME} > ${D}${sysconfdir}/prefs/properties/productLineName
    echo -n ${PRODUCT_DEVICE_NAME_PRODUCT_LINE_VERSION} > ${D}${sysconfdir}/prefs/properties/productLineVersion
    echo -n ${PRODUCT_DEVICE_NAME_PRODUCT_CLASS} > ${D}${sysconfdir}/prefs/properties/productClass
    echo -n ${PRODUCT_DEVICE_NAME_PRODUCT_BROWSER_OS_NAME} > ${D}${sysconfdir}/prefs/properties/browserOsName
    if [ ${PRODUCT_DEVICE_NAME_PRODUCT_BROWSER_OS_NAME} == "webOS" ]
    then
        echo -n ${PRODUCT_DEVICE_NAME_PRODUCT_BROWSER_OS_VERSION} > ${D}${sysconfdir}/prefs/properties/browserOsVersion
    fi
}

