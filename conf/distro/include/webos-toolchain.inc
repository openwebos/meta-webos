# (c) Copyright 2012-2013 Hewlett-Packard Development Company, L.P.
# (c) Copyright 2013 LG Electronics

TCMODE ?= "default"
require conf/distro/include/tcmode-${TCMODE}.inc

TCLIBC ?= "eglibc"
require conf/distro/include/tclibc-${TCLIBC}.inc

TCLIBCAPPEND ?= "-${TCLIBC}"
#TMPDIR .= "${TCLIBCAPPEND}"
