# (c) Copyright 2012  Hewlett-Packard Development Company, L.P. 
# (c) Copyright 2013 LG Electronics
#
# webos_daemon
#
# This class is to be inherited by the recipe for every component that installs
# a daemon.
#

# We expect all daemons will use pkgconfig when building.
inherit webos_pkgconfig
