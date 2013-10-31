# Copyright (c) 2013 LG Electronics, Inc.
#
# allarch
#

# Drop this when upgrading oe-core to 1.6 release or newer with this change:
# http://lists.openembedded.org/pipermail/openembedded-core/2013-November/086515.html
require ${COREBASE}/meta/classes/allarch.bbclass

python() {
    # oe-core allarch sets this only for PACKAGE_ARCH == "all" but we want it for all recipes
    # No need for virtual/libc or a cross compiler
    d.setVar("INHIBIT_DEFAULT_DEPS","1")

    # Allow this class to be included but overridden - only set
    # the values if we're still "all" package arch.
    if d.getVar("PACKAGE_ARCH") == "all":
        # External toolchain usually need to set different TARGET_PREFIX,
        # drop it here as allarch recipes shouldn't need any toolchain binaries
        d.setVar("TARGET_PREFIX", "")
}
