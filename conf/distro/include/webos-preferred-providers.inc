# Copyright (c) 2012-2014 LG Electronics, Inc.

# Place distro-wide PREFERRED_PROVIDER settings here; place MACHINE-specific
# settings in <MACHINE>.conf .

# Prefer libjpeg-turbo from meta-oe as it provides SIMD optimizations
PREFERRED_PROVIDER_jpeg = "libjpeg-turbo"
PREFERRED_PROVIDER_jpeg-native = "libjpeg-turbo-native"

# This line is required until all of the references to libjson are changed in metadata to json-c.
PREFERRED_PROVIDER_libjson = "json-c"

# Just for documentation; these values are the defaults set in the recipes that
# use them.
VIRTUAL-RUNTIME_cpushareholder = "cpushareholder-stub"
VIRTUAL-RUNTIME_rdx-utils = "rdx-utils-stub"

# Fonts used by the browser
VIRTUAL-RUNTIME_browser_fonts = "isis-fonts"

# TODO: find what is needed from initscripts and merge that to webos-initscripts
VIRTUAL-RUNTIME_initscripts = "initscripts webos-initscripts webos-shutdownscripts"

# Other alternatives are sysvinit, systemd
VIRTUAL-RUNTIME_init_manager = "upstart"

# With upstart we don't need update-rc.d, as bonus fixes following avahi issue for us:
# http://lists.openembedded.org/pipermail/openembedded-core/2013-November/086901.html
BAD_RECOMMENDATIONS += "update-rc.d"
UPDATERCD_class-target = ""

# Don't install big /boot/vmlinux* file by default in qemux86 images (included in oprofile's RRECOMMENDS)
# Use --no-vmlinux option to opcontrol or install it manually if you need to see samples from kernel
BAD_RECOMMENDATIONS_append_qemux86 = " kernel-vmlinux"

# Define which modules should be built by nyx-modules. As it is the base version
# for all module providers, nyx-modules interprets an empty list as "give me 
# everything". Any distribution which needs to specify other module providers
# must therefore override this variable and explicitly state which ones nyx
# modules should provide.
#
# Available options are:
#
# NYXMOD_OW_BATTERY    - Battery Status
# NYXMOD_OW_CHARGER    - Charger Control and Status
# NYXMOD_OW_DEVICEINFO - Device Information
# NYXMOD_OW_DISPLAY    - Display information
# NYXMOD_OW_KEYS       - Key (button) management
# NYXMOD_OW_OSINFO     - OS Version information
# NYXMOD_OW_SECURITY   - Security module
# NYXMOD_OW_SYSTEM     - System control and shutdown
# NYXMOD_OW_TOUCHPANEL - Touchpanel
NYX_MODULES_REQUIRED ?= ""

