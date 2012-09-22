# (c) Copyright 2012  Hewlett-Packard Development Company, L.P. 
#
# webos_daemon
#
# This class is to be inherited by the recipe for every component that installs
# a daemon.
#

# Currently, we use Upstart
RDEPENDS_${PN} += "upstart"
