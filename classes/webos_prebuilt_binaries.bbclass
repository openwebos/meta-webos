# Copyright (c) 2013 LG Electronics, Inc.
#
# webos_prebuilt_binaries
#
# If what's fetched by the SRC_URI for a component contains one or more prebuilt
# binaries (perhaps in addition to source), then its recipe must inherit from
# this bbclass.


# Set WEBOS_PREBUILT_BINARIES_FOR to the space separated list of MACHINEOVERRIDES
# values for which the binaries are available. It is processed into a value for
# COMPATIBLE_MACHINE.
#
# For ARM binaries, the value typically used is the list of ARMPKGARCH values
# corresponding to the architectures compatible with the -march argument used
# when the prebuilt binaries were compiled. If binaries are available for all
# ARM architecture MACHINE-s for which the distro is being built, use the value
# "arm". Note that since the MACHINEOVERRIDES values for little-endian and big-
# endian are the same, if a distro is being built for MACHINE-s with a mixture
# of endianness, binaries for a value specified in WEBOS_PREBUILT_BINARIES_FOR
# list must be available for both endian flavors.
#
# For Intel binaries, use "x86" if the prebuilt binaries were compiled with
# -m32, "x86_64" if they were compiled with -m64, and "x86_64_x32" if they were
# compiled with -mx32. (Note that the "x86_64*" values are not added to
# MACHINEOVERRIDES by the architecture tuning includes provided by oe-core; they
# must added explicitly by something in the BSP layer.
#
# There are also two special values: "all" means binaries are available for all
# of the MACHINE-s for which the distro is being built and "none" means binaries
# are not available for any of the MACHINE-s. The latter is intended to be used
# when WEBOS_PREBUILT_BINARIES_FOR will be overridden. If these are used, no
# other values can appear in WEBOS_PREBUILT_BINARIES_FOR.

WEBOS_PREBUILT_BINARIES_FOR ??= "none"

# Allow other assignments to COMPATIBLE_MACHINE done by the recipe to have
# precedence.
COMPATIBLE_MACHINE ??= "${@ \
    dict(all='^.*$', \
         none='^$', \
         arm='^armv.*$').get('${WEBOS_PREBUILT_BINARIES_FOR}', \
                             '^(' + '|'.join('${WEBOS_PREBUILT_BINARIES_FOR}'.split()) + ')$') }"
