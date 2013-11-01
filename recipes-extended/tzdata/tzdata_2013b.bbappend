# Copyright (c) 2013 LG Electronics, Inc.
# webOS system should be ready to have read-only /etc folder
# thus we move timezone/localtime to other place (in volatile partition)

EXTENDPRAUTO_append = "webos1"

do_install_append() {
    install -d "${D}/${webos_sysmgr_localstatedir}/preferences"

    # move what was installed with DEFAULT_TIMEZONE
    mv "${D}${sysconfdir}/timezone" "${D}${webos_sysmgr_localstatedir}/preferences/"
    mv "${D}${sysconfdir}/localtime" "${D}${webos_sysmgr_localstatedir}/preferences/"

    # make symlinks
    ln -s "${webos_sysmgr_localstatedir}/preferences/timezone" "${D}${sysconfdir}/"
    ln -s "${webos_sysmgr_localstatedir}/preferences/localtime" "${D}${sysconfdir}/"
}

# we replace current OE variant with our own based on original code
# please keep it in sync original OE variant or find a better solution
pkg_postinst_${PN}() {
    etc_lt=${LOCALTIME_LOCATION:-${webos_sysmgr_localstatedir}/preferences/localtime}
    src=${TIMEZONE_LOCATION:-${webos_sysmgr_localstatedir}/preferences/timezone}

    if [ -e ${src} ] ; then
        tz=$(sed -e 's:#.*::' -e 's:[[:space:]]*::g' -e '/^$/d' "${src}")
    fi

    if [ -z ${tz} ] ; then
        return 0
    fi

    if [ ! -e "$D${datadir}/zoneinfo/${tz}" ] ; then
        echo "You have an invalid TIMEZONE setting in ${src}"
        echo "Your ${etc_lt} has been reset to Universal; enjoy!"
        tz="Universal"
        echo "Updating ${etc_lt} with $D${datadir}/zoneinfo/${tz}"
        if [ -L ${etc_lt} ] ; then
            rm -f "${etc_lt}"
        fi
        ln -s "${datadir}/zoneinfo/${tz}" "${etc_lt}"
    fi
}

FILES_${PN} += "${webos_sysmgr_localstatedir}/preferences"
