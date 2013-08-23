# Copyright (c) 2013 LG Electronics, Inc.

EXTENDPRAUTO_append = "webos1"

# Explictly disable bzip2 support. Backport patch from oe-core
# http://lists.linuxtogo.org/pipermail/openembedded-core/2013-February/035778.html
# Remove this bbappend when oe-core is upgraded to revision including this fix
do_compile() {
    oe_runmake -f unix/Makefile generic IZ_BZIP2=no_such_directory
}
