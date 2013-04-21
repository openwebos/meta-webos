# Copyright (c) 2012 Hewlett-Packard Development Company, L.P.
#
# webos_oe_runmake_no_env_override
#
# By default, oe_runmake arranges for environment variables to override make
# variables. Inherit this class (before making any assignments to EXTRA_OEMAKE)
# to disable this. Typically, this is done when using a configurator that
# generates Makefiles with the expected settings from the environment already
# assigned to make variables (e.g. qmake).
#

EXTRA_OEMAKE = ""
