# (c) Copyright 2012  Hewlett-Packard Development Company, L.P. 

DESCRIPTION = "Retrieves system preferences values set and used by webOS"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"
SECTION = "webos/base"

DEPENDS += "luna-service2 cjson sqlite3 glib-2.0"

PR = "r2"

inherit webos_component
inherit webos_public_repo
inherit webos_submissions
inherit webos_cmake

WEBOS_GIT_TAG = "${WEBOS_SUBMISSION}"
SRC_URI = "${OPENWEBOS_GIT_REPO}/${PN};tag=${WEBOS_GIT_TAG};protocol=git"
S = "${WORKDIR}/git"

FILES_${PN} += "${bindir} ${libdir} ${sysconfdir} ${datadir}"

do_install_append() {
        # CFISH-930: remove "other" perms granted by pmmakefiles (aka palmmake):
        chmod o-rwx ${D}/usr/bin/luna-prefs-service
        chmod o-rwx ${D}/usr/bin/lunaprop

        install -d ${D}/etc/prefs/properties

        # Let's not require a submission process to add to the whitelist
        cat > ${D}/etc/prefs/public_properties <<EOF
com.palm.properties.nduid
EOF
        chmod 644 ${D}/etc/prefs/public_properties

        echo -n "${PRODUCT_DEVICE_NAME}"               > ${D}/etc/prefs/properties/deviceName
        echo -n "${PRODUCT_DEVICE_NAME_BRANDED}"       > ${D}/etc/prefs/properties/deviceNameBranded
        echo -n "${PRODUCT_DEVICE_NAME_SHORT}"         > ${D}/etc/prefs/properties/deviceNameShort
        echo -n "${PRODUCT_DEVICE_NAME_SHORT_BRANDED}" > ${D}/etc/prefs/properties/deviceNameShortBranded
        echo -n ${MACHINE} > ${D}/etc/prefs/properties/machineName
        echo -n ${PRODUCT_DEVICE_NAME_PRODUCT_LINE_NAME} > ${D}/etc/prefs/properties/productLineName
        echo -n ${PRODUCT_DEVICE_NAME_PRODUCT_LINE_VERSION} > ${D}/etc/prefs/properties/productLineVersion
        echo -n ${PRODUCT_DEVICE_NAME_PRODUCT_CLASS} > ${D}/etc/prefs/properties/productClass
        echo -n ${PRODUCT_DEVICE_NAME_PRODUCT_BROWSER_OS_NAME} > ${D}/etc/prefs/properties/browserOsName
        if [ ${PRODUCT_DEVICE_NAME_PRODUCT_BROWSER_OS_NAME} == "webOS" ]
        then
                echo -n ${PRODUCT_DEVICE_NAME_PRODUCT_BROWSER_OS_VERSION} > ${D}/etc/prefs/properties/browserOsVersion
        fi
}

