# Copyright (c) 2012-2013 LG Electronics, Inc.

#
# NOTE: Keep the lists in alphabetical order
#

# Upstream components don't have submissions
PREFERRED_VERSION_connman ?= "1.12"
PREFERRED_VERSION_db ?= "4.8.30"
PREFERRED_VERSION_libnih-native ?= "1.0.3"
PREFERRED_VERSION_libsoup-2.4 ?= "2.40.3"
PREFERRED_VERSION_linux-yocto ?= "3.4%"
PREFERRED_VERSION_nodejs ?= "0.10.15"
PREFERRED_VERSION_ntp ?= "4.2.6p5"
# Stick to latest udev version provided by oe-core
PREFERRED_VERSION_udev ?= "182"
PREFERRED_VERSION_upstart ?= "0.3.11"
PREFERRED_VERSION_valgrind ?= "3.8.1+svnr13429"
PREFERRED_VERSION_vboxguestdrivers ?= "4.2.6"
# Newer upstream meta-oe already has yajl 2.0.3 which isn't compatible with our libpbnjson yet
# https://github.com/openwebos/libpbnjson/pull/2
PREFERRED_VERSION_yajl ?= "1.0.12"

# Version for webos components is now defined directly in the recipes.
# Instead of setting PREFERRED_VERSION_foo = "version-submission" here, put it in foo.bb recipe as
# WEBOS_VERSION = "version-submission_revision" (revision is SHA-1 identifier of annotated submissions tag)
