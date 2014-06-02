# Copyright (c) 2012-2014 LG Electronics, Inc.

PKGV .= "-0webos1"
EXTENDPRAUTO_append = "webos2"

FILESEXTRAPATHS_prepend := "${THISDIR}/${BP}:"

# CVE-2013-1944
SRC_URI += "file://curl-tailmatch.patch"
SRC_URI += "file://CVE-2013-4545.patch"
SRC_URI += "file://cve-2013-6422.patch"
SRC_URI += "file://CVE-2014-0015.patch"
# CVE-2014-0139
SRC_URI += "file://libcurl-reject-cert-ip-wildcards.patch"
# CVE-2013-2174
SRC_URI += "file://libcurl-unescape.patch"

DEPENDS_append_class-target = " c-ares"

EXTRA_OECONF_append_class-target = " --enable-ares"
