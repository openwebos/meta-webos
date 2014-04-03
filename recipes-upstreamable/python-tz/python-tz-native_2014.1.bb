# Copyright (c) 2014 LG Electronics, Inc.

SUMMARY = "pytz brings the Olson tz database into Python"

DESCRIPTION="This library allows accurate and cross platform timezone calculations using Python 2.4 or higher"

HOMEPAGE = "https://pypi.python.org/pypi/pytz/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=22b38951eb857cf285a4560a914b7cd6"

PR = "r0"

SRC_URI = "https://pypi.python.org/packages/source/p/pytz/pytz-${PV}.tar.gz"

SRC_URI[md5sum] = "2c53d81a34aad3ad33f2545ca3ff910f"
SRC_URI[sha256sum] = "dcdde8b0034c0b70492e88a9416964126c81bae9254d8fd9d4367468ad11ded2"

S="${WORKDIR}/pytz-${PV}"

inherit native
inherit setuptools
