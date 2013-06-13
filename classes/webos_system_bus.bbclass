# Copyright (c) 2012-2013 LG Electronics, Inc.
#
# webos_system_bus

# This class is to be inherited by the recipe for every component that offers
# Luna System Bus services.
#
# The do_install() addition is skipped if WEBOS_SYSTEM_BUS_SKIP_DO_TASKS is "1".
#

RDEPENDS_${PN} += "luna-service2"

FILES_${PN} += "${webos_sysbus_prvservicesdir} ${webos_sysbus_pubservicesdir}"
FILES_${PN} += "${webos_sysbus_prvrolesdir}    ${webos_sysbus_pubrolesdir}"

webos_system_bus_install_files () {
    local _LS_PRV_DIR="${D}$1"    # destination directory for private hub files
    local _LS_PUB_DIR="${D}$2"    # destination directory for public hub files
    local _LS_PRV_FILE="$3"       # match string for private hub files
    local _LS_PUB_FILE="$4"       # match string for public hub files
    local i

    install -d $_LS_PRV_DIR
    install -d $_LS_PUB_DIR

    # Can't assume our current directory is still ${S}
    _LS_PUB=`find ${S}/service -name "$_LS_PUB_FILE"`
    _LS_PRV=`find ${S}/service -name "$_LS_PRV_FILE"`

    for i in $_LS_PUB; do
        _LS_PUB_DEST=`basename $i .pub`
        bbnote "PUBLIC: $_LS_PUB_DIR/$_LS_PUB_DEST"
        install -v -m 0644 $i $_LS_PUB_DIR/$_LS_PUB_DEST
    done

    for i in $_LS_PRV; do
        _LS_PRV_DEST=`basename $i .prv`
        bbnote "PRIVATE: $_LS_PRV_DIR/$_LS_PRV_DEST"
        install -v -m 0644 $i $_LS_PRV_DIR/$_LS_PRV_DEST
    done
}

do_install_append () {
    # Only want WEBOS_SYSTEM_BUS_SKIP_DO_TASKS to be expanded by bitbake => single quotes
    if [ '${WEBOS_SYSTEM_BUS_SKIP_DO_TASKS}' != 1 ]; then
        webos_system_bus_install_files ${webos_sysbus_prvservicesdir} ${webos_sysbus_pubservicesdir} "*.service.prv" "*.service.pub"
        webos_system_bus_install_files ${webos_sysbus_prvrolesdir}    ${webos_sysbus_pubrolesdir}    "*.json.prv"    "*.json.pub"
    fi
}
