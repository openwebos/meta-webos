# (c) Copyright 2012  Hewlett-Packard Development Company, L.P. 

DESCRIPTION = "Test components used in Open webOS"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

PR = "r1"

PACKAGES = "\
    ${PN} \
    ${PN}-dbg \
    ${PN}-dev \
    "

PACKAGE_ARCH = "${MACHINE_ARCH}"

ALLOW_EMPTY = "1"

# A group of Python 2 packages to develop and exercise tests for
# Open webOS processes.
# Will not be included in test image until the list name is added
# to the RDEPENDS list for this image.
PYTHON = " \
    python \
    python-datetime \
    python-debugger \
    python-difflib \
    python-json \
    python-netclient \
    python-pkgutil \
    python-pydoc \
    python-subprocess \
    python-unittest \
"

# to replace task-webos-test by packagegroup-webos-test during upgrade on target
RPROVIDES_${PN} = "task-webos-test"
RREPLACES_${PN} = "task-webos-test"
RCONFLICTS_${PN} = "task-webos-test"

RDEPENDS_${PN} = "\
     ltp \
"
