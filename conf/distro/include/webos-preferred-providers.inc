# (c) Copyright 2012  Hewlett-Packard Development Company, L.P.

# Place distro-wide PREFERRED_PROVIDER settings here; place MACHINE-specific
# settings in <MACHINE>.conf .

# Prefer libjpeg-turbo from meta-oe as it provides SIMD optimizations
PREFERRED_PROVIDER_jpeg = "libjpeg-turbo"
PREFERRED_PROVIDER_jpeg-native = "libjpeg-turbo-native"

# Keith says that image does not boot when only one is included
# TODO: find what is needed from initscripts and merge that to webos-initscripts
VIRTUAL-RUNTIME_initscripts = "initscripts webos-initscripts"

# Other alternatives are sysvinit, systemd
VIRTUAL-RUNTIME_init_manager = "upstart"
