# Copyright (c) 2013 LG Electronics, Inc.

PR_append = "webos1"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

do_install_append() {
    if [ ${BUILD_SYS} != ${HOST_SYS} ]; then
        if test -e ${D}${bindir} ; then
            for i in ${D}${bindir}/* ; do \
                sed -i -e s:/usr/bin/python-native/python:/usr/bin/python:g $i
            done
        fi
    fi
}

