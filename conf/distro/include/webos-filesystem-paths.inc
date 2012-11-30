# (c) Copyright 2012  Hewlett-Packard Development Company, L.P.

#
# Variables invented by webOS for standard locations
#

export webos_bootdir = "${base_prefix}/boot"
export webos_defaultconfdir = "${sysconfdir}/default"
export webos_execstatedir = "${localstatedir}/lib"
export webos_homedir = "${base_prefix}/home"
export webos_logdir = "${localstatedir}/log"
export webos_mediadir = "${base_prefix}/media"
export webos_mntdir = "${base_prefix}/mnt"
# Discourage use of ${libdir}/pkgconfig by not providing a variable for it
export webos_pkgconfigdir = "${datadir}/pkgconfig"
export webos_preservedtmpdir = "${localstatedir}/tmp"
export webos_runtimeinfodir = "${localstatedir}/run"
export webos_srcdir = "${prefix}/src"
export webos_upstartconfdir = "${sysconfdir}/event.d"


#
# Variables for webOS additions to the filesystem hierarchy
#

# Increment this every time values for the variables below change. But
# don't increment if merely adding a new variable for an existing location.
WEBOS_FILESYSTEM_LAYOUT_VERSION = "1"

export webos_prefix = "${prefix}/palm"
export webos_localstatedir = "${localstatedir}/palm"
export webos_sysconfdir = "${sysconfdir}/palm"

# The /palm/ subdirectories have been deliberately left as literals.
export webos_accttemplatesdir = "${prefix}/palm/public/accounts"
export webos_applicationsdir = "${prefix}/palm/applications"
export webos_frameworksdir = "${prefix}/palm/frameworks"
export webos_keysdir = "${prefix}/palm/data"
export webos_servicesdir = "${prefix}/palm/services"
export webos_soundsdir = "${prefix}/palm/sounds"
export webos_sysmgrdir = "${prefix}/palm/sysmgr"

export webos_db8datadir = "${localstatedir}/db"
export webos_filecachedir = "${localstatedir}/file-cache"
export webos_preferencesdir = "${localstatedir}/preferences"

export webos_sysbus_pubservicesdir = "${datadir}/dbus-1/services"
export webos_sysbus_prvservicesdir = "${datadir}/dbus-1/system-services"
export webos_sysbus_pubrolesdir = "${datadir}/ls2/roles/pub"
export webos_sysbus_prvrolesdir = "${datadir}/ls2/roles/prv"
export webos_sysbus_dynpubservicesdir = "${localstatedir}/palm/ls2/services/pub"
export webos_sysbus_dynprvservicesdir = "${localstatedir}/palm/ls2/services/prv"
export webos_sysbus_dynpubrolesdir = "${localstatedir}/palm/ls2/roles/pub"
export webos_sysbus_dynprvrolesdir = "${localstatedir}/palm/ls2/roles/prv"

export webos_sysmgr_datadir = "${libdir}/luna"
export webos_sysmgr_localstatedir = "${localstatedir}/luna"

export webos_browserpluginsdir = "${libdir}/BrowserPlugins"
export webos_qtpluginsdir = "${prefix}/plugins"

export webos_cryptofsdir = "${webos_mediadir}/cryptofs"
export webos_appstoragedir = "${webos_cryptofsdir}/apps"
export webos_browserstoragedir = "${webos_cryptofsdir}/.browser"
export webos_installedappsdir = "${webos_appstoragedir}/usr/palm/applications"

export webos_localstoragedir = "${webos_mediadir}/internal"
