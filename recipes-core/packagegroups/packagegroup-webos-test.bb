# Copyright (c) 2012 Hewlett-Packard Development Company, L.P.
# Copyright (c) 2013 LG Electronics, Inc.

DESCRIPTION = "Test components used in Open webOS"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

PR = "r5"

inherit packagegroup

PACKAGE_ARCH = "${MACHINE_ARCH}"

# A group of Python 2 packages to develop and exercise tests for
# Open webOS processes.
# Will not be included in test image until the list name is added
# to the RDEPENDS list for this image.
PYTHON = " \
    python \
    python-2to3 \
    python-audio \
    python-bsddb \
    python-codecs \
    python-compile \
    python-compiler \
    python-compression \
    python-core \
    python-crypt \
    python-ctypes \
    python-curses \
    python-datetime \
    python-db \
    python-dbus \
    python-debugger \
    python-difflib \
    python-distutils \
    python-distutils-staticdev \
    python-elementtree \
    python-email \
    python-fcntl \
    python-gdbm \
    python-hotshot \
    python-html \
    python-idle \
    python-image \
    python-io \
    python-json \
    python-lang \
    python-logging \
    python-mailbox \
    python-man \
    python-math \
    python-mime \
    python-misc \
    python-mmap \
    python-modules \
    python-multiprocessing \
    python-netclient \
    python-netserver \
    python-numbers \
    python-pickle \
    python-pkgutil \
    python-pprint \
    python-profile \
    python-pydoc \
    python-pygobject \
    python-re \
    python-readline \
    python-resource \
    python-robotparser \
    python-setuptools \
    python-shell \
    python-smtpd \
    python-sqlite3 \
    python-sqlite3-tests \
    python-stringold \
    python-subprocess \
    python-syslog \
    python-terminal \
    python-tests \
    python-textutils \
    python-threading \
    python-tkinter \
    python-unittest \
    python-unixadmin \
    python-xml \
    python-xmlrpc \
    python-zlib \
"

# to replace task-webos-test by packagegroup-webos-test during upgrade on target
RPROVIDES_${PN} = "task-webos-test"
RREPLACES_${PN} = "task-webos-test"
RCONFLICTS_${PN} = "task-webos-test"

RDEPENDS_${PN} = "\
    ltp \
    ${PYTHON} \
    valgrind \
"
