# Copyright (c) 2014 LG Electronics, Inc.

# Drop this .bbappend when upgrading to newer version, which will
# include this patches:
# http://patch-tracker.debian.org/patch/series/view/lighttpd/1.4.28-2+squeeze1.5/cve-2013-4508.patch
# http://patch-tracker.debian.org/patch/series/view/lighttpd/1.4.28-2+squeeze1.5/cve-2013-4559.patch
# http://patch-tracker.debian.org/patch/series/view/lighttpd/1.4.28-2+squeeze1.5/cve-2013-4560.patch
# Version where issues are fixed: 1.4.35
#
# http://download.lighttpd.net/lighttpd/security/lighttpd-1.4.34_fix_mysql_injection.patch
# Affected all versions up to and including 1.4.34. The fix for CVE-2014-2323 also fixes CVE-2014-2324.


PKGV .= "-0webos2"
EXTENDPRAUTO_append = "webos2"

FILESEXTRAPATHS_prepend := "${THISDIR}/${BP}:"

SRC_URI += "file://lighttpd-cve-2013-4508.patch"
SRC_URI += "file://lighttpd-cve-2013-4559.patch"
SRC_URI += "file://lighttpd-cve-2013-4560.patch"
SRC_URI += "file://lighttpd-cve-2014-2323_2324.patch"
