# Copyright (c) 2013  LG Electronics, Inc.

DESCRIPTION = "Node.js native addon build tool"
HOMEPAGE = "https://github.com/TooTallNate/node-gyp"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=694e396551033371686c80d3a1a69e88"

PR = "r1"

SRC_URI = "https://github.com/TooTallNate/node-gyp/archive/v${PV}.tar.gz"
SRC_URI[md5sum] = "35fbdcd6a48a20d3ba99989748743b51"
SRC_URI[sha256sum] = "18e6e5fd0670c9ee8cf89dfa91831b5fa1abc68d92dc800a669b33c7bae260f0"

S = "${WORKDIR}/node-gyp-${PV}"

inherit native

DEPENDS = "nodejs-native"

do_install () {
    #force npm to install modules to correct place
    export npm_config_prefix=${D}${prefix}
    #install from fetched files
    ${STAGING_LIBDIR_NATIVE}/node_modules/npm/bin/npm-cli.js -g install ${S}
}
