# (c) Copyright 2012  Hewlett-Packard Development Company, L.P. 
#
# webos_system_bus

# This class is to be inherited by the recipe for every component that offers
# Luna System Bus services.
#
# The do_install() addition is skipped if WEBOS_SYSTEM_BUS_SKIP_DO_TASKS is "1".
#

RDEPENDS += "luna-service2"

# XXX Should be global -- see [OWEBOS-2424]
webos_sysbus_pubservicesdir = "${datadir}/dbus-1/services"
webos_sysbus_prvservicesdir = "${datadir}/dbus-1/system-services"
webos_sysbus_pubrolesdir = "${datadir}/ls2/roles/pub"
webos_sysbus_prvrolesdir = "${datadir}/ls2/roles/prv"

FILES_${PN} += "${webos_sysbus_prvservicesdir} ${webos_sysbus_pubservicesdir}"
FILES_${PN} += "${webos_sysbus_prvrolesdir} ${webos_sysbus_pubrolesdir}"

webos_system_bus_install_files () {
    local _LS_PRV_DIR="${D}$1"    # destination directory for private hub files
    local _LS_PUB_DIR="${D}$2"    # destination directory for public hub files
    local _LS_PRV_FILE="$3"       # match string for private hub files
    local _LS_PUB_FILE="$4"       # match string for public hub files
    local i

    install -d $_LS_PRV_DIR
    install -d $_LS_PUB_DIR

    _LS_PUB=`find service -name "$_LS_PUB_FILE"`
    _LS_PRV=`find service -name "$_LS_PRV_FILE"`

    for i in $_LS_PUB; do
        _LS_PUB_DEST=`basename $i .pub`
        oenote "PUBLIC: $_LS_PUB_DIR/$_LS_PUB_DEST"
        install -m 0644 $i $_LS_PUB_DIR/$_LS_PUB_DEST
    done

    for i in $_LS_PRV; do
        _LS_PRV_DEST=`basename $i .prv`
        oenote "PRIVATE: $_LS_PRV_DIR/$_LS_PRV_DEST"
        install -m 0644 $i $_LS_PRV_DIR/$_LS_PRV_DEST
    done
}

do_install_append () {
    # Only want WEBOS_SYSTEM_BUS_SKIP_DO_TASKS to be expanded by bitbake => single quotes
    if [ '${WEBOS_SYSTEM_BUS_SKIP_DO_TASKS}' != 1 ]; then
        webos_system_bus_install_files ${webos_sysbus_prvservicesdir} ${webos_sysbus_pubservicesdir} "*.service.prv" "*.service.pub"
        webos_system_bus_install_files ${webos_sysbus_prvrolesdir}    ${webos_sysbus_pubrolesdir}    "*.json.prv"    "*.json.pub"
    fi
}
