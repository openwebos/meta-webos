# Copyright (c) 2012-2013 LG Electronics, Inc.

EXTENDPRAUTO_append = "webos2"

# All the original recipe does is stage a tarball and some autotools files;
# nothing compiled.
inherit allarch

# Nothing in mm-common is installed on the target => the base package should
# be empty. What's below works because ${PN}-dev is packaged ahead of ${PN}.
FILES_${PN}-dev += "${FILES_${PN}}"
