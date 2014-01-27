# Copyright (c) 2012-2013 LG Electronics, Inc.

#
# NOTE: Keep the lists in alphabetical order
#

PREFERRED_VERSION_connman ?= "1.12"
PREFERRED_VERSION_db ?= "4.8.30"
PREFERRED_VERSION_libnih-native ?= "1.0.3"
PREFERRED_VERSION_libsoup-2.4 ?= "2.40.3"
PREFERRED_VERSION_linux-yocto ?= "3.4%"
PREFERRED_VERSION_nodejs ?= "0.10.15"
PREFERRED_VERSION_ntp ?= "4.2.6p5"
# non-GPLv3 versions of these components
PREFERRED_VERSION_gmp = "4.2.1"
PREFERRED_VERSION_gzip = "1.3.12"
PREFERRED_VERSION_readline ?= "5.2"
PREFERRED_VERSION_tar ?= "1.17"
# Stick to latest udev version provided by oe-core
PREFERRED_VERSION_udev ?= "182"
PREFERRED_VERSION_upstart ?= "0.3.11"
PREFERRED_VERSION_vboxguestdrivers ?= "4.2.6"
# Newer upstream meta-oe already has yajl 2.0.3, and we should upgrade to it soon,
# because it's already compatible with our libpbnjson.
PREFERRED_VERSION_yajl ?= "1.0.12"

# Versions for webOS components are now defined directly in their recipes.
# Instead of setting PREFERRED_VERSION_foo = "<component-version>-<submission>"
# here, put it in foo.bb recipe just above the PR assignment as:
#
#   WEBOS_VERSION = "<component-version>-<submission>_<revision-hash>"
#
# where <revision-hash> is the 40-character SHA-1 identifier of annotated
# submissions tag.
