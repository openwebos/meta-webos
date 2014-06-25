# Copyright (c) 2014 LG Electronics, Inc.

EXTENDPRAUTO_append = "webos1"

do_install_append () {
    rm -f ${D}${bindir}/wiper.sh
}
