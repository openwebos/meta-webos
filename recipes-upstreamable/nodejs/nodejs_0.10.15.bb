DESCRIPTION = "nodeJS Evented I/O for V8 JavaScript"
HOMEPAGE = "http://nodejs.org"
LICENSE = "MIT & BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1b19aee7bf088151c559f3ec9f830b44"

DEPENDS = "openssl"

PR = "r1"

SRC_URI = "http://nodejs.org/dist/v${PV}/node-v${PV}.tar.gz"
SRC_URI[md5sum] = "59f295b0a30dc8dbdb46407c2d9b2923"
SRC_URI[sha256sum] = "87345ab3b96aa02c5250d7b5ae1d80e620e8ae2a7f509f7fa18c4aaa340953e8"

S = "${WORKDIR}/node-v${PV}"

# v8 errors out if you have set CCACHE
CCACHE = ""

ARCHFLAGS_arm = "${@bb.utils.contains('TUNE_FEATURES', 'callconvention-hard', '--with-arm-float-abi=hard', '--with-arm-float-abi=softfp', d)}"
ARCHFLAGS ?= ""

# Node is way too cool to use proper autotools, so we install two wrappers to forcefully inject proper arch cflags to workaround gypi
do_configure () {
    export LD="${CXX}"
    # $TARGET_ARCH settings don't match --dest-cpu settings
    if [ "${TARGET_ARCH}" = "arm" ]; then
        ./configure --prefix=${prefix} --without-snapshot --shared-openssl --dest-cpu=arm --dest-os=linux ${ARCHFLAGS}
    elif [ "${TARGET_ARCH}" = "x86_64" ]; then
        ./configure --prefix=${prefix} --shared-openssl --dest-cpu=x64
    else
        ./configure --prefix=${prefix} --shared-openssl --dest-cpu=ia32
    fi
}

do_compile () {
    export LD="${CXX}"
    make BUILDTYPE=Release
}

do_install () {
    oe_runmake install DESTDIR=${D}
}

do_install_append_class-native() {
    # use node from PATH instead of absolute path to sysroot
    # node-v0.10.15/tools/install.py is using:
    # shebang = os.path.join(node_prefix, 'bin/node')
    # update_shebang(link_path, shebang)
    # and node_prefix can be very long path to bindir in native sysroot and
    # when it exceeds 128 character shebang limit it's stripped to incorrect path
    # and npm fails to execute like in this case with 133 characters show in log.do_install:
    # updating shebang of /home/jenkins/workspace/build-webos-nightly/device/qemux86/label/open-webos-builder/BUILD-qemux86/work/x86_64-linux/nodejs-native/0.10.15-r0/image/home/jenkins/workspace/build-webos-nightly/device/qemux86/label/open-webos-builder/BUILD-qemux86/sysroots/x86_64-linux/usr/bin/npm to /home/jenkins/workspace/build-webos-nightly/device/qemux86/label/open-webos-builder/BUILD-qemux86/sysroots/x86_64-linux/usr/bin/node
    sed "1s^.*^#\!/usr/bin/env node^g" -i ${D}${bindir}/npm
}

do_install_append_class-target() {
    sed "1s^.*^#\!${bindir}/env node^g" -i ${D}${bindir}/npm
}

RDEPENDS_${PN} = "curl python-shell python-datetime python-subprocess python-textutils"
RDEPENDS_${PN}_class-native = ""

FILES_${PN} += "${libdir}/node_modules ${libdir}/dtrace"
BBCLASSEXTEND = "native"
