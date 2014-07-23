# Copyright (c) 2014 LG Electronics, Inc.
#
# webos_nyx_module_provider

# This class is to be inherited by the recipe for every component that offers
# nyx modules
#

# If no modules from this provider are needed for an image, then it might
# produce an empty package
ALLOW_EMPTY_${PN} = "1"

# Pass in the list of required modules
#
# CMake requires that a list contain no embedded whitespace, so we clean
# it up first.

def webos_clean_nyxrequired(s):
    return  ";".join(filter(None,map(str.strip,s.split(';'))))
    
NYX_MODULES_REQUIRED ??= ""
CLEAN_MODULE_LIST = "${@ webos_clean_nyxrequired('${NYX_MODULES_REQUIRED}')}"
EXTRA_OECMAKE += "-DNYX_MODULES_REQUIRED:STRING='${CLEAN_MODULE_LIST}'"

# Ensure any nyx-module provider cites a dependency on nyx-lib
DEPENDS += "nyx-lib"

# Add any built modules are installed
FILES_${PN} += "${libdir}/nyx/modules/*"
FILES_${PN}-dbg += "${libdir}/nyx/modules/.debug/*"

