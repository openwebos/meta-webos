# (c) Copyright 2012  Hewlett-Packard Development Company, L.P. 

require qt4-webos.inc

# XXX We don't depend on qmake-webos-native because we continue to build qmake-palm during
# do_configure() -- see commentary in qmake-webos-native.bb
DEPENDS = "freetype jpeg libpng zlib glib-2.0 nyx-lib"

PR = "r16"

inherit webos_public_repo
inherit webos_oe_runmake_no_env_override
inherit webos_submissions
inherit webos_library

inherit webos_machine_dep

QT4_MACHINE_CONFIG_ARCH_LITE_QPA = "-qpa"
QT4_MACHINE_CONFIG_ARCH_LITE_QPA_arm = "-arch arm -qpa"
QT4_MACHINE_CONFIG_FLAGS = "-xplatform qws/linux-armv6-g++ -no-opengl -no-neon -no-rpath -DPALM_DEVICE -qconfig palm"
QT4_MACHINE_CONFIG_FLAGS_x86 = "-xplatform qws/linux-qemux86-g++ -no-neon -no-rpath -DPALM_DEVICE -qconfig palm"
QT4_MACHINE_CONFIG_FLAGS_armv7a = "-xplatform qws/linux-armv6-g++ -opengl -plugin-gfx-egl -DPALM_DEVICE -qconfig palm"

PALM_BUILD_DIR = "${S}/../qt-build-${MACHINE}"
QT4_STAGING_BUILD_DIR = "/usr/src/qt4-webos"

# Export the current configuration out so that Qt .pro files can utilize these during
# their configuration
export WEBOS_CONFIG="webos ${MACHINE}"

do_install() {
    # Don't install qmake (already done by qmake-webos-native), but do install mkspecs,
    # since it contains a MACHINE-dependent qconfig.pri (this is because QT_CONFIG_FLAGS
    # is MACHINE-dependent). It's also target-dependent, since configure tries to
    # auto-config many of the settings.
    oe_runmake -C ${PALM_BUILD_DIR} INSTALL_ROOT=${D} install_mkspecs install_subtargets

    # Nova-Main has these additional files installed
    install -v -d ${D}${includedir}/QtOpenGL
    install -v -m 644 ${S}/src/opengl/gl2paintengineex/qglcustomshaderstage_p.h ${D}${includedir}/QtOpenGL
    if [ -d ${D}/usr/imports/Qt/labs/shaders ]; then
        install -v -d ${D}/usr/plugins/imports/Qt/labs
	    mv -v ${D}/usr/imports/Qt/labs/shaders ${D}/usr/plugins/imports/Qt/labs
    fi

    # The *_location settings in the Qt*.pc files don't make any sense since they
    # refer to native utilities => remove them.
    for pc in ${D}${libdir}/pkgconfig/Qt*.pc; do
        outf=${D}/usr/lib/pkgconfig/$(basename $pc)
        echo "Removing  _location settings from $pc"
        sed -i -e '/^[^_]*_location=/ d' $pc
    done
    
    # work around for building webkit-supplemental without qt4-webos BUILDDIR
    install -d ${D}${QT4_STAGING_BUILD_DIR}/git/src
    cp -ra ${S}/src/gui ${D}${QT4_STAGING_BUILD_DIR}/git/src
    cp -ra ${S}/src/corelib ${D}${QT4_STAGING_BUILD_DIR}/git/src

    install -d ${D}${QT4_STAGING_BUILD_DIR}/git/src/3rdparty
    cp -ra ${S}/src/3rdparty/harfbuzz ${D}${QT4_STAGING_BUILD_DIR}/git/src/3rdparty
    install -d ${D}${QT4_STAGING_BUILD_DIR}/git/src/3rdparty/webkit/
    cp -ra ${S}/src/3rdparty/webkit/include ${D}${QT4_STAGING_BUILD_DIR}/git/src/3rdparty/webkit/

    install -d ${D}${QT4_STAGING_BUILD_DIR}/build
    install -d ${D}${QT4_STAGING_BUILD_DIR}/build/src
    cp -ra ${PALM_BUILD_DIR}/include/ ${D}${QT4_STAGING_BUILD_DIR}/build
    cp -ra ${PALM_BUILD_DIR}/src/corelib ${D}${QT4_STAGING_BUILD_DIR}/build/src

    # XXX Nova-Main has libqpalm.so under ${libdir} as well as /usr/plugins because that's
    # where the link of luna-sysmgr expects to find it. luna-sysmgr will be changed by
    # [OWEBOS-2617] to look for it under /usr/plugins so that we don't have to install
    # libqpalm.so in two places. (It's a problem for it to need to be in ${libdir} because
    # the default FILES_${PN}-dev pick will pick it up from there.)

    # XXX Remove files not installed by Nova-Main. Eventually, figure out how to configure
    # so that they're not installed.
    # WARNING: This assumes that ${D} has a per-component value
    set -x
    rm -rf ${D}${datadir}
    rm -rf ${D}${bindir}
    rm -rf ${D}${libdir}/fonts
    rm -rf ${D}${prefix}/plugins/bearer
    rm -rf ${D}${prefix}/plugins/generic
    rm -rf ${D}${prefix}/plugins/inputmethods

    # XXX Can we get away with not staging /usr/lib/*.prl and ignoring the imports tree
    # (it seems as though we copy what we want from imports into plugins/imports)?
    rm -f ${D}${libdir}/*.prl
    rm -rf ${D}${prefix}/imports
    set +x
}

sysroot_stage_all_append() {
        sysroot_stage_dir ${D}${QT4_STAGING_BUILD_DIR} ${SYSROOT_DESTDIR}${QT4_STAGING_BUILD_DIR}
}

PACKAGES += "${PN}-buildsrc"

FILES_${PN} += "/usr/plugins"
# Yes, qmake-webos IS correct: mkspecs are dependent on the target.
FILES_${PN}-dev += "${libdir}/qmake-webos"
FILES_${PN}-dbg += "/usr/plugins/*/.debug"
FILES_${PN}-dbg += "/usr/plugins/imports/Qt/labs/shaders/.debug"
FILES_${PN}-buildsrc += "${QT4_STAGING_BUILD_DIR}"
