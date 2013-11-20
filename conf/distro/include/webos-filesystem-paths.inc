# Copyright (c) 2012-2013 LG Electronics, Inc.

#
# Variables invented by webOS for standard locations
#

export webos_bootdir = "${base_prefix}/boot"
export webos_browserpluginsdir = "${libdir}/BrowserPlugins"
export webos_defaultconfdir = "${sysconfdir}/default"
export webos_execstatedir = "${localstatedir}/lib"
export webos_fontsdir = "${datadir}/fonts"
export webos_homedir = "${base_prefix}/home"
export webos_firmwaredir = "${base_libdir}/firmware"
export webos_logdir = "${localstatedir}/log"
export webos_mediadir = "${base_prefix}/media"
export webos_mntdir = "${base_prefix}/mnt"
# Discourage use of ${libdir}/pkgconfig by not providing a variable for it.
export webos_pkgconfigdir = "${datadir}/pkgconfig"
export webos_preservedtmpdir = "${localstatedir}/tmp"
# Having a Qt plugins directory is standard, but the value used by Open webOS isn't.
export webos_qtpluginsdir = "${prefix}/plugins"
export webos_runtimeinfodir = "${localstatedir}/run"
export webos_srcdir = "${prefix}/src"
export webos_udevscriptsdir = "${base_libdir}/udev"
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
# This is the location of webOS applications, both JS and native. There is a
# subdirectory tree for each application that is named using its complete name.
export webos_applicationsdir = "${prefix}/palm/applications"
export webos_frameworksdir = "${prefix}/palm/frameworks"
export webos_keysdir = "${prefix}/palm/data"
# This is the location of the pre-installed catalog apps IPKs
export webos_picapkgdir = "${prefix}/palm/ipkgs"
# This is the location of webOS application plugins. There is a subdirectory for
# each application that is named using the final field of its complete name.
export webos_pluginsdir = "${prefix}/palm/plugins"
# This is the location of the trees for JS services; the files for native (dynamic)
# services are located under sbindir, libdir, etc. as if they were Linux daemons.
export webos_servicesdir = "${prefix}/palm/services"
export webos_smartkeydatadir = "${prefix}/palm/smartkey"
export webos_soundsdir = "${prefix}/palm/sounds"
export webos_sysmgrdir = "${prefix}/palm/sysmgr"

# Note that everything under localstatedir is erased by a NYX_SYSTEM_ERASE_VAR
# erasure.
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
export webos_sysbus_devpubservicesdir = "${localstatedir}/palm/ls2-dev/services/pub"
export webos_sysbus_devprvservicesdir = "${localstatedir}/palm/ls2-dev/services/prv"
export webos_sysbus_devpubrolesdir = "${localstatedir}/palm/ls2-dev/roles/pub"
export webos_sysbus_devprvrolesdir = "${localstatedir}/palm/ls2-dev/roles/prv"

export webos_sysmgr_datadir = "${libdir}/luna"
export webos_sysmgr_localstatedir = "${localstatedir}/luna"

export webos_cryptofsdir = "${webos_mediadir}/cryptofs"

# Everything under this tree is erased by a NYX_SYSTEM_ERASE_VAR erasure.
export webos_browserstoragedir = "${webos_cryptofsdir}/.browser"

# This is the tree for components downloaded from the app catalog; everything
# under this tree is erased by a NYX_SYSTEM_ERASE_VAR erasure.
export webos_downloadeddir = "${webos_cryptofsdir}/apps"
# The old name for webos_downloadeddir
export webos_appstoragedir = "${webos_downloadeddir}"
export webos_downloaded_applicationsdir = "${webos_downloadeddir}/usr/palm/applications"
# The old name for webos_downloaded_applicationsdir
export webos_installedappsdir = "${webos_downloaded_applicationsdir}"
export webos_downloaded_frameworksdir = "${webos_downloadeddir}/usr/palm/frameworks"
export webos_downloaded_pluginsdir = "${webos_downloadeddir}/usr/palm/plugins"
export webos_downloaded_servicesdir = "${webos_downloadeddir}/usr/palm/services"

# The specs for webos_execstatedir apply to this location with the additional
# constraint that everything under this tree persists a NYX_SYSTEM_ERASE_VAR
# erasure. It is only deleted by a NYX_SYSTEM_ERASE_ALL erasure.
export webos_persistentstoragedir = "${webos_cryptofsdir}/data"

# db8 database use this directory to store mediadb database.
# mediadb database store results of fileindexer and contain information
# about media files. Potentially this db can be big
export webos_db8mediadir = "${webos_persistentstoragedir}/db8/mediadb"

# On devices that support it, this tree is externally mountable as (USB) mass
# storage. Applications that want their data to be visible in this manner should
# store them here instead of under webos_persistentstoragedir. This tree is
# erased by NYX_SYSTEM_ERASE_MEDIA.
export webos_mountablestoragedir = "${webos_mediadir}/internal"
# The old name for webos_mountablestoragedir
export webos_localstoragedir = "${webos_mountablestoragedir}"

# This is the tree for components loaded while in developer mode; everything
# under this tree is erased by a NYX_SYSTEM_ERASE_DEVELOPER erasure.
export webos_developerdir = "${webos_mediadir}/developer"
