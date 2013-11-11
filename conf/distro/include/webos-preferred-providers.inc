# Copyright (c) 2012-2013 LG Electronics, Inc.

# Place distro-wide PREFERRED_PROVIDER settings here; place MACHINE-specific
# settings in <MACHINE>.conf .

# Prefer libjpeg-turbo from meta-oe as it provides SIMD optimizations
PREFERRED_PROVIDER_jpeg = "libjpeg-turbo"
PREFERRED_PROVIDER_jpeg-native = "libjpeg-turbo-native"

# Just for documentation; these values are the defaults set in the recipes that
# use them.
VIRTUAL-RUNTIME_cpushareholder = "cpushareholder-stub"
VIRTUAL-RUNTIME_rdx-utils = "rdx-utils-stub"

# Fonts used by the browser
VIRTUAL-RUNTIME_browser_fonts = "isis-fonts"

# TODO: find what is needed from initscripts and merge that to webos-initscripts
VIRTUAL-RUNTIME_initscripts = "initscripts webos-initscripts"

# Other alternatives are sysvinit, systemd
VIRTUAL-RUNTIME_init_manager = "upstart"

# Remove this when oe-core is upgraded to include
# http://git.openembedded.org/openembedded-core/commit/?id=f5d018a769fa297efa629cbbf6e42a49173faa8b
# Weak assignment is OK here, because we're not changing existing
# PREFERRED_PROVIDER, we're just backporting this line from newer default-providers.inc
PREFERRED_PROVIDER_udev ?= "${@base_contains('DISTRO_FEATURES','systemd','systemd','udev',d)}"
