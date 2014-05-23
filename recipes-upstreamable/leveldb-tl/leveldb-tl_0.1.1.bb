# Copyright (c) 2014 LG Electronics, Inc.

SUMMARY = "LevelDB Template Library"
DESCRIPTION = "Template library to build a more complex storage schema with leveldb as a backend"
AUTHOR = "Nikolay Orliuk <virkony@gmail.com>"
HOMEPAGE = "https://github.com/ony/leveldb-tl"
SECTION = "libs"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=3b83ef96387f14655fc854ddc3c6bd57"
DEPENDS = "leveldb gtest"
PR = "r0"

SRC_URI = "git://github.com/ony/leveldb-tl.git;branch=gcc-4.7"

# Use gcc-4.7/v0.1.1 tag. It's backport of gcc-4.8 branch,
# so it would work fine with both compiler versions
SRCREV = "255a1c98a4da577f7a3825a8ebcc48ac98d09acf"

S = "${WORKDIR}/git"

inherit cmake
