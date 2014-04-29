# Copyright (c) 2014 LG Electronics, Inc.

PKGV .= "-0webos1"

EXTENDPRAUTO_append = "webos1"
FILESEXTRAPATHS_prepend := "${THISDIR}/${BP}:"

SRC_URI += "file://texinfo.patch"

# from http://patch-tracker.debian.org/
# there is one more version of this patch which brings regression
# so please if you want to change something be careful
SRC_URI += "file://CVE-2007-6720.patch"

# from http://patch-tracker.debian.org/
SRC_URI += "file://CVE-2009-0179.patch"

# imported from http://pkgs.fedoraproject.org/git/libmikmod.git
# removed CVE-2009-3995 part because of inconsistancy
SRC_URI += "file://CVE-2009-3996.patch"

# fix for CVE-2010-2546 is not integrated because
# there is CVE-2010-2971.patch in OE which doing the same -
# correct fix of CVE-2009-3995
# Also pay attention that original CVE-2010-2971.patch
# (from ubuntu for example) also wants remove inconsistance
# fix of CVE-2009-3995 but CVE-2010-2971.patch from OE
# doesn't contain this fix and it is correct because
# CVE-2009-3995.patch was not applied
