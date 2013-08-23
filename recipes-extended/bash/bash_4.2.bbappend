# Copyright (c) 2012-2013 LG Electronics, Inc.

EXTENDPRAUTO_append = "webos1"

# We don't want bash to ever be /bin/sh: our upstart conf files don't work when
# it is -- apparently it handles quotes on the command line differently from
# the ash supplied by busybox. Prevent the /bin/sh symlink to bash from being
# created by unsetting the ALTERNATIVE_* variables set in bash.inc .
ALTERNATIVE_${PN} = ""
ALTERNATIVE_LINK_NAME[sh] = ""
ALTERNATIVE_TARGET[sh] = ""
ALTERNATIVE_PRIORITY = ""

