# Copyright (c) 2014 LG Electronics, Inc.

EXTENDPRAUTO_append = "webos2"

# drop this .bbappend when upgrading to oe-core/daisy
# with this commit d294f1de082a5aadefa8efb3485ca3f091ef83bf

FILES_${PN} = "${base_sbindir}/hdparm*"
