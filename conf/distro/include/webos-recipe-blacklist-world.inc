# Copyright (c) 2013 LG Electronics

# A blacklisted recipe is ignored by bitbake. The reason it is useful to be able
# to have bitbake ignore a recipe is because, although PREFERRED_PROVIDER can be
# used to select which recipe is used to fulfill a build-time dependency, bitbake
# does not have a PREFERRED_RPROVIDER to allow specification of which package
# should be used to satisfy a run-time dependency. When two recipes both provide
# common packages, but one recipe provides additional packages that the other
# doesn't, then the only way to guarantee that the first recipe will never be
# used is to blacklist it.

# Be aware that changes in this file usually doesn't trigger reparsing
# to force it manually you can remove BUILD-*/cache directory, this will
# be solved by backporting this patch into bitbake version we're using:
# http://lists.openembedded.org/pipermail/bitbake-devel/2013-July/003624.html
 
# Unused in webOS world, blacklisted to allow to build world faster

PNBLACKLIST[qt4-x11-free] = "unused in webOS world"
PNBLACKLIST[qt4-embedded] = "unused in webOS world"
PNBLACKLIST[webkit-gtk] = "unused in webOS world"

PNBLACKLIST[llvm2.8] = "we prefer 3.2+"
PNBLACKLIST[llvm2.9] = "we prefer 3.2+"
PNBLACKLIST[llvm2.8-native] = "we prefer 3.2+"
PNBLACKLIST[llvm2.9-native] = "we prefer 3.2+"

PNBLACKLIST[packagegroup-core-qt] = "rdepends on blacklisted quicky, fotowall"
PNBLACKLIST[packagegroup-qt-toolchain-target] = "rdepends on blacklisted qt4-mkspecs, libqtmultimedia4-dev, libqtphonon4-dev, libqt3support4-dev, libqtclucene4-dev, libqtcore4-dev, libqtdbus4-dev, libqtdesignercomponents4-dev, libqtdesigner4-dev, libqtuitools4-dev, libqtgui4-dev, libqthelp4-dev, libqtnetwork4-dev, libqtscript4-dev, libqtscripttools4-dev, libqtsql4-dev, libqtsvg4-dev, libqttest4-dev, libqtwebkit4-dev, libqtxml4-dev, libqtdeclarative4-dev, libqtxmlpatterns4-dev, qt4-x11-free-dev, libqtopengl4-dev"
PNBLACKLIST[packagegroup-qte-toolchain-target] = "rdepends on blacklisted qt4-embedded-mkspecs, libqt-embeddedmultimedia4-dev, libqt-embeddedphonon4-dev, libqt-embedded3support4-dev, libqt-embeddedclucene4-dev, libqt-embeddedcore4-dev, libqt-embeddeddbus4-dev, libqt-embeddeddesignercomponents4-dev, libqt-embeddeddesigner4-dev, libqt-embeddeduitools4-dev, libqt-embeddedgui4-dev, libqt-embeddedhelp4-dev, libqt-embeddednetwork4-dev, libqt-embeddedscript4-dev, libqt-embeddedscripttools4-dev, libqt-embeddedsql4-dev, libqt-embeddedsvg4-dev, libqt-embeddedtest4-dev, libqt-embeddedwebkit4-dev, libqt-embeddedxml4-dev, libqt-embeddeddeclarative4-dev, libqt-embeddedxmlpatterns4-dev"
PNBLACKLIST[packagegroup-core-qt4e] = "rdepends on blacklisted qt4-embedded, libqt-embedded3support4, libqt-embeddedclucene4, libqt-embeddedcore4, libqt-embeddeddbus4, libqt-embeddedgui4, libqt-embeddedhelp4, libqt-embeddedmultimedia4, libqt-embeddednetwork4, libqt-embeddedscript4, libqt-embeddedscripttools4, libqt-embeddedsql4, libqt-embeddedsvg4, libqt-embeddedtest4, libqt-embeddedwebkit4, libqt-embeddedxml4, qt4-embedded-fonts-ttf-dejavu, qt4-embedded-fonts-ttf-vera, qt4-embedded-plugin-iconengine-svgicon, qt4-embedded-plugin-imageformat-gif, qt4-embedded-plugin-imageformat-ico, qt4-embedded-plugin-imageformat-jpeg, qt4-embedded-plugin-imageformat-mng, qt4-embedded-plugin-imageformat-svg, qt4-embedded-plugin-imageformat-tiff, qt4-embedded-plugin-mousedriver-tslib, qt4-embedded-plugin-phonon-backend-gstreamer, qt4-embedded-plugin-script-dbus, qt4-embedded-plugin-sqldriver-sqlite, qt4-embedded-demos, qt4-embedded-examples, qt4-embedded-assistant, packagegroup-core-qt4e"

# Unused and fails to fetch:
PNBLACKLIST[pcsc-lite] = "unused in webOS world and fails to fetch"
PNBLACKLIST[uhd-firmware] = "unused in webOS world and fails to fetch"
PNBLACKLIST[ccid] = "unused in webOS world and fails to fetch"
PNBLACKLIST[lzip] = "unused in webOS world and fails to fetch"
# Fixed in newer meta-oe http://git.openembedded.org/meta-openembedded/commit/?id=2cab9ba0d4d72496633b92b67ac1a63f605c5ada
PNBLACKLIST[claws-plugin-gtkhtml2-viewer] = "unused in webOS world and fails to fetch"
PNBLACKLIST[claws-plugin-mailmbox] = "unused in webOS world and fails to fetch"
PNBLACKLIST[claws-plugin-rssyl] = "unused in webOS world and fails to fetch"

# Depends on x11 DISTRO_FEATURE
PNBLACKLIST[tk] = "depends on x11 DISTRO_FEATURE"
PNBLACKLIST[xdotool] = "depends on x11 DISTRO_FEATURE"
PNBLACKLIST[openbox] = "depends on x11 DISTRO_FEATURE"
PNBLACKLIST[xf86-video-omapfb] = "depends on x11 DISTRO_FEATURE"
PNBLACKLIST[clutter-1.8] = "depends on x11 DISTRO_FEATURE"
PNBLACKLIST[xrdb] = "depends on x11 DISTRO_FEATURE"
PNBLACKLIST[startup-notification] = "depends on x11 DISTRO_FEATURE"
PNBLACKLIST[xkbcomp] = "depends on x11 DISTRO_FEATURE"
PNBLACKLIST[libxsettings-client] = "depends on x11 DISTRO_FEATURE"
PNBLACKLIST[links-x11] = "depends on x11 DISTRO_FEATURE"
PNBLACKLIST[fstests] = "depends on x11 DISTRO_FEATURE"
PNBLACKLIST[setxkbmap] = "depends on x11 DISTRO_FEATURE"
PNBLACKLIST[consolekit] = "depends on x11 DISTRO_FEATURE"
PNBLACKLIST[xprop] = "depends on x11 DISTRO_FEATURE"
PNBLACKLIST[glew] = "depends on x11 DISTRO_FEATURE"
PNBLACKLIST[qt4-x11-free] = "depends on x11 DISTRO_FEATURE"
PNBLACKLIST[numlockx] = "depends on x11 DISTRO_FEATURE"
PNBLACKLIST[mkfontscale] = "depends on x11 DISTRO_FEATURE"
PNBLACKLIST[iceauth] = "depends on x11 DISTRO_FEATURE"
PNBLACKLIST[xvinfo] = "depends on x11 DISTRO_FEATURE"
PNBLACKLIST[xinput] = "depends on x11 DISTRO_FEATURE"
PNBLACKLIST[matchbox-wm] = "depends on x11 DISTRO_FEATURE"
PNBLACKLIST[xmodmap] = "depends on x11 DISTRO_FEATURE"
PNBLACKLIST[xev] = "depends on x11 DISTRO_FEATURE"
PNBLACKLIST[xclock] = "depends on x11 DISTRO_FEATURE"
PNBLACKLIST[openmotif] = "depends on x11 DISTRO_FEATURE"
PNBLACKLIST[pong-clock] = "depends on x11 DISTRO_FEATURE"
PNBLACKLIST[mplayer2] = "depends on x11 DISTRO_FEATURE"
PNBLACKLIST[t1lib] = "depends on x11 DISTRO_FEATURE"
PNBLACKLIST[xinit] = "depends on x11 DISTRO_FEATURE"
PNBLACKLIST[gnome-desktop] = "depends on x11 DISTRO_FEATURE"
PNBLACKLIST[xdpyinfo] = "depends on x11 DISTRO_FEATURE"
PNBLACKLIST[sessreg] = "depends on x11 DISTRO_FEATURE"
PNBLACKLIST[mkfontdir] = "depends on x11 DISTRO_FEATURE"
PNBLACKLIST[x11vnc] = "depends on x11 DISTRO_FEATURE"
PNBLACKLIST[lxdm] = "depends on x11 DISTRO_FEATURE"
PNBLACKLIST[qemu] = "depends on x11 DISTRO_FEATURE"
PNBLACKLIST[x11perf] = "depends on x11 DISTRO_FEATURE"
PNBLACKLIST[xf86-video-omap] = "depends on x11 DISTRO_FEATURE"
PNBLACKLIST[xrestop] = "depends on x11 DISTRO_FEATURE"
PNBLACKLIST[xpext] = "depends on x11 DISTRO_FEATURE"
PNBLACKLIST[xauth] = "depends on x11 DISTRO_FEATURE"
PNBLACKLIST[rgb] = "depends on x11 DISTRO_FEATURE"
PNBLACKLIST[xwininfo] = "depends on x11 DISTRO_FEATURE"
PNBLACKLIST[freerdp] = "depends on x11 DISTRO_FEATURE"
PNBLACKLIST[xsp] = "depends on x11 DISTRO_FEATURE"
PNBLACKLIST[cogl] = "depends on x11 DISTRO_FEATURE"
PNBLACKLIST[xeyes] = "depends on x11 DISTRO_FEATURE"
PNBLACKLIST[xrandr] = "depends on x11 DISTRO_FEATURE"
PNBLACKLIST[xtscal] = "depends on x11 DISTRO_FEATURE"
PNBLACKLIST[twm] = "depends on x11 DISTRO_FEATURE"
PNBLACKLIST[xset] = "depends on x11 DISTRO_FEATURE"
PNBLACKLIST[xhost] = "depends on x11 DISTRO_FEATURE"
PNBLACKLIST[xinput-calibrator] = "depends on x11 DISTRO_FEATURE"
PNBLACKLIST[gnuplot] = "depends on x11 DISTRO_FEATURE"
PNBLACKLIST[libmatchbox] = "depends on x11 DISTRO_FEATURE"

# Depends on recipe with incompatible LICENSE
PNBLACKLIST[libsdl-mixer] = "depends on blacklisted libmad"
# PNBLACKLIST[vlc] = "depends on blacklisted qt4-x11-free, libxpm, fluidsynth, libmad, mpeg2dec"

# Depends on failing recipe:
PNBLACKLIST[uhd] = "depends on blacklisted uhd-firmware"

# Depends on unused recipe:
PNBLACKLIST[qt-mobility-embedded] = "depends on blacklisted qt4-embedded"
PNBLACKLIST[qconnman-e] = "depends on blacklisted qt4-embedded"
PNBLACKLIST[qtserialport-e] = "depends on blacklisted qt4-embedded"
PNBLACKLIST[web-webkit] = "depends on blacklisted webkit-gtk"
PNBLACKLIST[gimp] = "depends on blacklisted webkit-gtk"

# Depends on recipe which require x11 DISTRO_FEATURE
PNBLACKLIST[qconnman] = "depends on blacklisted qt4-x11-free"
PNBLACKLIST[python-sip] = "depends on blacklisted qt4-x11-free"
PNBLACKLIST[python-pyqt] = "depends on blacklisted qt4-x11-free"
PNBLACKLIST[qcanobserver-socketcan] = "depends on blacklisted qt4-x11-free"
PNBLACKLIST[qcanobserver] = "depends on blacklisted qt4-x11-free"
PNBLACKLIST[maliit-framework] = "depends on blacklisted qt4-x11-free"
PNBLACKLIST[maliit-plugins] = "depends on blacklisted qt4-x11-free"
PNBLACKLIST[fingerterm] = "depends on blacklisted qt4-x11-free"
PNBLACKLIST[libconnman-qt] = "depends on blacklisted qt4-x11-free"
PNBLACKLIST[qextserialport] = "depends on blacklisted qt4-x11-free"
PNBLACKLIST[quicky] = "depends on blacklisted qt4-x11-free"
PNBLACKLIST[fotowall] = "depends on blacklisted qt4-x11-free"
PNBLACKLIST[monav] = "depends on blacklisted qt4-x11-free"
PNBLACKLIST[qwt] = "depends on blacklisted qt4-x11-free"
PNBLACKLIST[vlc] = "depends on blacklisted qt4-x11-free, libxpm, fluidsynth, libmad, mpeg2dec"
PNBLACKLIST[navit] = "depends on blacklisted qt4-x11-free"
PNBLACKLIST[gnuradio] = "depends on blacklisted qt4-x11-free"
PNBLACKLIST[qt-mobility-x11] = "depends on blacklisted qt4-x11-free"
PNBLACKLIST[clutter-box2d] = "depends on blacklisted clutter-1.8"
PNBLACKLIST[clutter-gst-1.8] = "depends on blacklisted clutter-1.8"
PNBLACKLIST[clutter-gtk-1.8] = "depends on blacklisted clutter-1.8"
PNBLACKLIST[mx] = "depends on blacklisted clutter-1.8, startup-notification, libxrandr"
PNBLACKLIST[xterm] = "depends on blacklisted libxpm, libxau, libxext, libxaw"
PNBLACKLIST[puzzles] = "depends on blacklisted libxt"
PNBLACKLIST[synergy] = "depends on blacklisted libx11, libxtst"
PNBLACKLIST[waffle] = "depends on blacklisted libx11"
PNBLACKLIST[libfakekey] = "depends on blacklisted libxtst"
PNBLACKLIST[gtkmathview] = "depends on blacklisted t1lib"
PNBLACKLIST[pidgin] = "depends on blacklisted startup-notification"
PNBLACKLIST[matchbox-desktop] = "depends on blacklisted startup-notification"
PNBLACKLIST[matchbox-panel-2] = "depends on blacklisted startup-notification"
PNBLACKLIST[matchbox-keyboard] = "depends on blacklisted libxft"
PNBLACKLIST[matchbox-theme-sato] = "depends on blacklisted matchbox-wm"
PNBLACKLIST[pcmanfm] = "depends on blacklisted startup-notification"
PNBLACKLIST[libxcb] = "depends on blacklisted libxau, libxdmcp"
PNBLACKLIST[gtk+3] = "depends on blacklisted libxrandr, libxext, libxcursor, libxdamage, libxrender, libxcomposite, libxi"
PNBLACKLIST[fltk] = "depends on blacklisted libxext, libxft"
PNBLACKLIST[xserver-xorg] = "depends on blacklisted libxext"
PNBLACKLIST[xcursorgen] = "depends on blacklisted libxcursor"
PNBLACKLIST[xf86-input-synaptics] = "depends on blacklisted libxi"
PNBLACKLIST[xvideo-tests] = "depends on blacklisted libxv"
PNBLACKLIST[xbitmaps] = "depends on blacklisted libxmu"
PNBLACKLIST[xcb-util] = "depends on blacklisted libxcb"
PNBLACKLIST[xcb-util-image] = "depends on blacklisted libxcb"
PNBLACKLIST[xcb-util-renderutil] = "depends on blacklisted libxcb"
PNBLACKLIST[xcb-util-wm] = "depends on blacklisted libxcb"
PNBLACKLIST[xcb-util-keysyms] = "depends on blacklisted libxcb"
PNBLACKLIST[xf86-input-mtev] = "depends on blacklisted xserver-xorg"
PNBLACKLIST[xf86-input-keyboard] = "depends on blacklisted xserver-xorg"
PNBLACKLIST[xf86-input-evdev] = "depends on blacklisted xserver-xorg"
PNBLACKLIST[xf86-video-fbdev] = "depends on blacklisted xserver-xorg"
PNBLACKLIST[xf86-input-mouse] = "depends on blacklisted xserver-xorg"
PNBLACKLIST[xf86-video-modesetting] = "depends on blacklisted xserver-xorg"
PNBLACKLIST[xf86-video-glamo] = "depends on blacklisted xserver-xorg"
PNBLACKLIST[xf86-input-void] = "depends on blacklisted xserver-xorg"
PNBLACKLIST[xf86-input-tslib] = "depends on blacklisted xserver-xorg"
PNBLACKLIST[xf86-video-vmware] = "depends on blacklisted xserver-xorg, libxvmc"
PNBLACKLIST[xf86-video-intel] = "depends on blacklisted xserver-xorg"
PNBLACKLIST[xf86-input-vmmouse] = "depends on blacklisted xserver-xorg"
PNBLACKLIST[xf86-video-geode] = "depends on blacklisted xserver-xorg"
PNBLACKLIST[xf86-video-vesa] = "depends on blacklisted xserver-xorg, libxvmc"
PNBLACKLIST[libcanberra] = "depends on blacklisted gtk+3"
PNBLACKLIST[pavucontrol] = "depends on blacklisted libcanberra"
PNBLACKLIST[sato-screenshot] = "depends on blacklisted matchbox-panel-2"
PNBLACKLIST[networkmanager] = "depends on blacklisted polkit"
PNBLACKLIST[upower] = "depends on blacklisted polkit"
PNBLACKLIST[devicekit-power] = "depends on blacklisted polkit"
PNBLACKLIST[udisks] = "depends on blacklisted polkit"
PNBLACKLIST[polkit-gnome] = "depends on blacklisted polkit"
PNBLACKLIST[packagekit] = "depends on blacklisted polkit"
PNBLACKLIST[xorg-cf-files] = "depends on blacklisted font-util"
PNBLACKLIST[mkfontscale-native] = "depends on blacklisted virtual/libx11-native, libfontenc-native"
PNBLACKLIST[mkfontdir-native] = "depends on blacklisted virtual/libx11-native"
PNBLACKLIST[mkfontscale] = "depends on blacklisted libfontenc-native"
PNBLACKLIST[encodings] = "depends on blacklisted mkfontscale-native"
PNBLACKLIST[imake] = "depends on blacklisted xorg-cf-files"
#PNBLACKLIST[] = "depends on blacklisted "

PNBLACKLIST[packagegroup-core-directfb] = "rdepends on blacklisted directfb, directfb-examples"
PNBLACKLIST[matchbox-session-sato] = "rdepends on blacklisted matchbox-theme-sato, matchbox-panel-2, matchbox-session-sato-dev"
PNBLACKLIST[packagegroup-core-x11-sato] = "rdepends on blacklisted matchbox-session-sato, matchbox-desktop, matchbox-keyboard, matchbox-keyboard-applet, matchbox-keyboard-im, packagegroup-core-x11-sato, x11vnc, sato-screenshot, pcmanfm, packagegroup-core-x11-sato-base, packagegroup-core-x11-sato-apps, packagegroup-core-x11-sato-games"
PNBLACKLIST[anki] = "rdepends on blacklisted python-pyqt, python-sip"
PNBLACKLIST[polkit] = "rdepends on blacklisted consolekit"
PNBLACKLIST[packagegroup-core-x11] = "rdepends on blacklisted xauth, xhost, xset"
PNBLACKLIST[xserver-common] = "rdepends on blacklisted xrandr, xinput-calibrator"
PNBLACKLIST[packagegroup-core-x11-base] = "rdepends on blacklisted packagegroup-core-x11-utils"
PNBLACKLIST[packagegroup-core-lsb] = "rdepends on blacklisted libqtcore4, libqtgui4, libqtsql4, libqtsvg4, libqtxml4, libqtnetwork4, libxt, libxxf86vm, libxi, libxtst, qt4-plugin-sqldriver-sqlite, libx11-locale, libqtopengl4, packagegroup-core-sys-extended, packagegroup-core-db, packagegroup-core-perl, packagegroup-core-python, packagegroup-core-tcl, packagegroup-core-lsb-misc, packagegroup-core-lsb-core, packagegroup-core-lsb-perl, packagegroup-core-lsb-python, packagegroup-core-lsb-desktop, packagegroup-core-lsb-runtime-add"
PNBLACKLIST[font-util] = "rdepends on blacklisted mkfontdir, mkfontscale"
PNBLACKLIST[font-alias] = "rdepends on blacklisted font-util"
PNBLACKLIST[packagegroup-core-standalone-gmae-sdk-target] = "rdepends on blacklisted libxi-dev, gnome-desktop"
PNBLACKLIST[x11-common] = "rdepends on blacklisted xinit, xmodmap, xdpyinfo, xtscal"
PNBLACKLIST[packagegroup-core-x11-xserver] = "rdepends on blacklisted xserver-xorg, xf86-video-fbdev, xf86-input-evdev"
PNBLACKLIST[packagegroup-core-x11-base] = "rdepends on blacklisted matchbox-wm"
PNBLACKLIST[packagegroup-core-tools-testapps] = "rdepends on blacklisted fstests, x11perf, xrestop, xwininfo, xprop, xvideo-tests"
PNBLACKLIST[xorg-minimal-fonts] = "rdepends on blacklisted font-alias"
PNBLACKLIST[packagegroup-self-hosted] = "rdepends on blacklisted libx11-dev"
PNBLACKLIST[zaurusd] = "rdepends on blacklisted xrandr"
PNBLACKLIST[packagegroup-core-sdk-gmae] = "rdepends on blacklisted libxi-dev, gnome-desktop"
PNBLACKLIST[xserver-nodm-init] = "rdepends on blacklisted xserver-common, xinit"
PNBLACKLIST[packagegroup-core-clutter] = "rdepends on blacklisted clutter-1.8, clutter-gst-1.8, clutter-gtk-1.8"
#PNBLACKLIST[] = "rdepends on blacklisted "

PNBLACKLIST[net-snmp] = "broken: net-snmp-5.7.2/agent/snmpd.c:459: undefined reference to `netsnmp_sd_listen_fds'"
PNBLACKLIST[emacs] = "broken: qemu dies while running qemu-treedir qemu: uncaught target signal 11 (Segmentation fault) - core dumped"
PNBLACKLIST[rpm] = "broken: rpm-5.4.9/rpmdb/.libs/librpmdb.so: undefined reference to `db3Free'"
PNBLACKLIST[storaged] = "broken: [GF-8105] storaged-2.1.0-5 isn't compatible with current nyx-lib-5.1.0-66"
PNBLACKLIST[mg] = "broken: Missing ncurses dependency: Looking for term.h...  Not found!"
PNBLACKLIST[gtk+] = "broken: cairo-xlib isn't availabe without x11 DISTRO_FEATURES: No package 'cairo-xlib' found"
PNBLACKLIST[ode] = "broken: configure:6512: error: cannot run test program while cross compiling"
PNBLACKLIST[libglu] = "broken: we don't build libGL with mesa: cannot find -lGL"
PNBLACKLIST[gst-plugins-gl] = "broken: depends on X11 headers: gstglwindow_x11ES2.c:29:22: fatal error: X11/Xlib.h: No such file or directory"
# remove this when using libav from libav_git.bb which doesn't create libpostproc packages and DEPENDS on libpostproc
PNBLACKLIST[libpostproc] = "broken: conflicts with packages created by libav-0.8.4"
# cloud9 isn't compatible with 0.10 nodejs https://github.com/ajaxorg/cloud9/issues/2741
# proper fix would be to retain 0.8 version in nodejs8 (like we do with nodejs4_0.4.12) and update cloud9 dependency to nodejs8
PNBLACKLIST[cloud9] = "broken: isn't compatible with our nodejs"

# cannot blacklist this, too many things depends on it
#PNBLACKLIST[ltrace] = "broken: error: 'BREAKPOINT_LENGTH' undeclared here (not in a function) "
#PNBLACKLIST[packagegroup-core-tools-debug] = "rdepends on broken ltrace"
#PNBLACKLIST[packagegroup-cli-tools] = "rdepends on broken ltrace"
#PNBLACKLIST[alsa-plugins] = "broken: alsa-plugins-1.0.26/a52/pcm_a52.c:412: undefined reference to `av_free'"
#PNBLACKLIST[] = "broken: "

# Fixes for missing dependencies were already sent upstream, but aren't in 1.4 Dylan release
# fixed in http://lists.openembedded.org/pipermail/openembedded-devel/2013-July/091278.html
PNBLACKLIST[f2fs-tools] = "broken: missing dependency on util-linux"
# all 5 fixed in http://lists.openembedded.org/pipermail/openembedded-core/2013-July/080806.html
PNBLACKLIST[libtelepathy] = "broken: missing dependency on libxslt-native"
PNBLACKLIST[telepathy-idle] = "broken: missing dependency on libxslt-native"
PNBLACKLIST[telepathy-glib] = "broken: missing dependency on libxslt-native"
PNBLACKLIST[telepathy-python] = "broken: missing dependency on libxslt-native"
PNBLACKLIST[telepathy-mission-control] = "broken: missing dependency on libxslt-native"
# fixed in http://git.openembedded.org/meta-openembedded/commit/?id=46354a697c18a64c5836159adf8dbd92e6547e85
PNBLACKLIST[vsftpd] = "broken: missing dependency on openssl"
# fixed in http://lists.openembedded.org/pipermail/openembedded-devel/2013-July/091281.html
PNBLACKLIST[cpufrequtils] = "broken: missing dependency on gettext-native"
# fixed in http://lists.openembedded.org/pipermail/openembedded-devel/2013-July/091283.html
PNBLACKLIST[gsoap] = "broken: missing dependency on zlib"
# fixed in http://lists.openembedded.org/pipermail/openembedded-devel/2013-July/091284.html
PNBLACKLIST[libspatialite] = "broken: missing dependency on sqlite3"
# fixed in http://lists.openembedded.org/pipermail/openembedded-devel/2013-July/091285.html
PNBLACKLIST[wbxml2] = "broken: missing dependency on expat"
# fixed in http://lists.openembedded.org/pipermail/openembedded-devel/2013-July/091280.html
PNBLACKLIST[xorg-sgml-doctools] = "broken: missing dependency on util-macros"

# fixed in http://lists.openembedded.org/pipermail/openembedded-devel/2013-July/091287.html
PNBLACKLIST[iso-codes] = "broken: missing dependency on gettext-native"
PNBLACKLIST[pangomm] = "broken: missing dependency on pango with cairo enabled"
# fixed in http://git.openembedded.org/meta-openembedded/commit/?id=11e767eeead8850c291687357dbe4428423d6193
PNBLACKLIST[python-feedparser] = "broken: missing dependency on python-setuptools-native (inherits distutils instead of setuptools)"
# fixed in http://git.openembedded.org/openembedded-core/commit/?id=e54393f18e2ffa5d8dbeca2357b0b3b7e4e31829
PNBLACKLIST[wget] = "broken: missing dependency on zlib"
PNBLACKLIST[php] = "depends on broken php-native"
#PNBLACKLIST[] = "broken: missing dependency on "

PNBLACKLIST[libsyncml] = "depends on broken wbxml2"
PNBLACKLIST[packagegroup-basic] = "depends on broken cpufrequtils"
# cannot blacklist this, too many things depends on it
# fixed in http://lists.openembedded.org/pipermail/openembedded-core/2013-July/080807.html
#PNBLACKLIST[gst-plugins-bad] = "broken: missing dependency on glib-2.0-native"
# fixed in http://lists.openembedded.org/pipermail/openembedded-core/2013-July/080808.html
#PNBLACKLIST[gst-plugins-base] = "broken: missing dependency on glib-2.0-native"
# fixed in http://lists.openembedded.org/pipermail/openembedded-devel/2013-July/091282.html
#PNBLACKLIST[cryptsetup] = "broken: missing dependency on libgcrypt"
# fixed in http://lists.openembedded.org/pipermail/openembedded-core/2013-July/080809.html
#PNBLACKLIST[taglib] = "broken: missing dependency on zlib"
# fixed in http://lists.openembedded.org/pipermail/openembedded-core/2013-July/080811.html
#PNBLACKLIST[libpam] = "broken: missing dependency on pkgconfig-native"
#PNBLACKLIST[libuser] = "depends on broken libpam"
#PNBLACKLIST[libc-client] = "depends on broken libpam"
# fixed in http://git.openembedded.org/openembedded-core/commit/?id=d9f9bfed56ef8562256fc01c3e42e15734230c3a
#PNBLACKLIST[libarchive] = "broken: many underterministic deps lzma, acl, bzip2, openssl"
# fixed in http://lists.openembedded.org/pipermail/openembedded-core/2013-July/080813.html
#PNBLACKLIST[eglibc-locale] = "broken: missing dependency on binutils-cross"
# fixed in http://lists.openembedded.org/pipermail/openembedded-core/2013-July/080893.html
#PNBLACKLIST[luna-sysmgr-common] = "broken: [GF-8536] missing dependency on binutils-cross"
# fixed in http://lists.openembedded.org/pipermail/openembedded-core/2013-July/080812.html
#PNBLACKLIST[systemtap-native] = "broken: missing dependency on pkgconfig-native"
#PNBLACKLIST[systemtap-uprobes] = "depends on broken systemtap-native"
#PNBLACKLIST[systemtap] = "depends on broken systemtap-native"
# fixed in http://git.openembedded.org/openembedded-core/commit/?id=2b47bce78536a800205b2385bba69038351545e5
#PNBLACKLIST[gcc] = "broken: fails when libunwind is built before gcc is"
# fixed in http://git.openembedded.org/openembedded-core/commit/?id=dde80e6fac83ca55644cb1b56cb55b2ba01c6564
#PNBLACKLIST[alsa-tools] = "broken: aclocal missing path to sysroot"
# fixed in http://lists.openembedded.org/pipermail/openembedded-core/2013-July/080810.html
#PNBLACKLIST[quota] = "broken: missing dependency on pkgconfig-native"
#PNBLACKLIST[rpcbind] = "depends on broken quota"
#PNBLACKLIST[nfs-utils] = "depends on broken rpcbind"
#PNBLACKLIST[packagegroup-base] = "depends on broken rpcbind"
#PNBLACKLIST[packagegroup-core-basic] = "depends on broken rpcbind"
#PNBLACKLIST[] = "depends on broken "

# fixed in http://git.openembedded.org/meta-openembedded/commit/?id=c21c656bfacfacc64b51dbf6357a25e46728f8ab
PNBLACKLIST[cyrus-sasl] = "autodetects krb5 and fails selecting gssapi"
# fixed in http://lists.openembedded.org/pipermail/openembedded-core/2013-July/081566.html
PNBLACKLIST[subversion] = "autodetects cyrus-sasl"
# fixed in http://git.openembedded.org/meta-openembedded/commit/?id=4cba066de9173fd5776d57f7a29860d81db4acc0
PNBLACKLIST[libetpan] = "autodetects cyrus-sasl"
# fixed in http://git.openembedded.org/meta-openembedded/commit/?id=dcbadeb68f65d909d34508f766b529ab73246336
PNBLACKLIST[mysql5] = "broken: sometimes ignores system zlib and stages own bundled version causing issues to other packages"
PNBLACKLIST[libdbi-drivers] = "depends on broken mysql5"
PNBLACKLIST[gammu] = "depends on broken mysql5"
# fixed in http://git.openembedded.org/meta-openembedded/commit/?id=c1127efaa4ef7a4afe6fdc51b07602d8ee68d6a4
PNBLACKLIST[media-ctl] = "missing pkgconfig inherit"
# fixed in http://git.openembedded.org/openembedded-core/commit/?id=defa05f2085f78d9ec9ada7051c284e1fc72e6c1
PNBLACKLIST[bdwgc] = "missing pkgconfig inherit"
PNBLACKLIST[guile] = "depends on broken bdwgc"
# fixed in http://git.openembedded.org/meta-openembedded/commit/?id=d048fd049f154b273b09f86816f8296de11327e5
PNBLACKLIST[rsyslog] = "missing pkgconfig inherit"
# fixed in http://git.openembedded.org/meta-openembedded/commit/?id=e2aa5eca92fabc5c90909b00700782b7030beec1
PNBLACKLIST[portaudio-v19] = "missing pkgconfig inherit"
PNBLACKLIST[espeak] = "depends on broken portaudio-v19"
# fixed in http://git.openembedded.org/meta-openembedded/commit/?id=83872967b3784c9f006a6dd17ac46f466e3f2038
PNBLACKLIST[gypsy] = "missing dependency on libxslt-native"

PNBLACKLIST[rpmresolve] = "depends on broken rpm"
PNBLACKLIST[python-smartpm] = "depends on broken rpm"
PNBLACKLIST[gtk-sato-engine] = "depends on broken gtk+"
PNBLACKLIST[gegl] = "depends on broken gtk+"
PNBLACKLIST[libowl-av] = "depends on broken gtk+"
PNBLACKLIST[vala] = "depends on broken gtk+"
PNBLACKLIST[oh-puzzles] = "depends on broken gtk+"
PNBLACKLIST[connman-gnome] = "depends on broken gtk+"
PNBLACKLIST[subsurface] = "depends on broken gtk+"
PNBLACKLIST[librsvg] = "depends on broken gtk+"
PNBLACKLIST[gtk-engines] = "depends on broken gtk+"
PNBLACKLIST[vte] = "depends on broken gtk+"
PNBLACKLIST[obex-data-server] = "depends on broken gtk+"
PNBLACKLIST[poppler] = "depends on broken gtk+"
PNBLACKLIST[leafpad] = "depends on broken gtk+"
PNBLACKLIST[matchbox-terminal] = "depends on broken gtk+"
PNBLACKLIST[opencv] = "depends on broken gtk+"
PNBLACKLIST[libsexy] = "depends on broken gtk+"
PNBLACKLIST[geany] = "depends on broken gtk+"
PNBLACKLIST[gtk-theme-torturer] = "depends on broken gtk+"
PNBLACKLIST[libowl] = "depends on broken gtk+"
PNBLACKLIST[gtkhtml2] = "depends on broken gtk+"
PNBLACKLIST[gaku] = "depends on broken gtk+"
PNBLACKLIST[kernelshark] = "depends on broken gtk+"
PNBLACKLIST[python-pygtk] = "depends on broken gtk+"
PNBLACKLIST[foxtrotgps] = "depends on broken gtk+"
PNBLACKLIST[sysprof] = "depends on broken gtk+"
PNBLACKLIST[gnome-keyring] = "depends on broken gtk+"
PNBLACKLIST[orrery] = "depends on broken gtk+"
PNBLACKLIST[settings-daemon] = "depends on broken gtk+"
PNBLACKLIST[glade3] = "depends on broken gtk+"
PNBLACKLIST[libunique] = "depends on broken gtk+"
PNBLACKLIST[omgps] = "depends on broken gtk+"
PNBLACKLIST[libglade] = "depends on broken gtk+"
PNBLACKLIST[avahi-ui] = "depends on broken gtk+"
PNBLACKLIST[sylpheed] = "depends on broken gtk+"
PNBLACKLIST[matchbox-config-gtk] = "depends on broken gtk+"
PNBLACKLIST[libwmf] = "depends on broken gtk+"
PNBLACKLIST[gtkmm] = "depends on broken gtk+"
PNBLACKLIST[xchat] = "depends on broken gtk+"
PNBLACKLIST[oprofileui] = "depends on broken gtk+"
PNBLACKLIST[libfm] = "depends on broken gtk+"
PNBLACKLIST[claws-mail] = "depends on broken gtk+"
PNBLACKLIST[icon-slicer] = "depends on broken gtk+"

# Depends on something blacklisted because the dependency was depending on something broken
PNBLACKLIST[vala-dbus-binding-tool] = "depends on blacklisted vala"
PNBLACKLIST[opencv-samples] = "depends on blacklisted opencv"
PNBLACKLIST[owl-video] = "depends on blacklisted libowl-av"
PNBLACKLIST[toscoterm] = "depends on blacklisted vte"
PNBLACKLIST[vala-terminal] = "depends on blacklisted vte"
PNBLACKLIST[imagemagick] = "depends on blacklisted librsvg"
#PNBLACKLIST[] = "depends on blacklisted "

PNBLACKLIST[python-gst] = "rdepends on blacklisted python-pygtk"
PNBLACKLIST[gnome-icon-theme] = "rdepends on blacklisted librsvg-gtk"
#PNBLACKLIST[] = "rdepends on blacklisted "

PNBLACKLIST[lsbtest] = "rdepends on broken rpm"
#PNBLACKLIST[] = "rdepends on broken "

# these are specific only for webos DISTRO
python () {
    if d.getVar('DISTRO', True) == 'webos':
        d.setVarFlag('PNBLACKLIST', 'mesa-demos', 'depends on blacklisted glew')
        d.setVarFlag('PNBLACKLIST', 'weston', 'broken: doesn\'t build with old mesa and without exported FLAGS to disable X11 headers')
}
# this syntax doesn't work:
# PNBLACKLIST_webos[mesa-demos] = "depends on blacklisted glew"

# upstream recipes with known QA issues
# /usr/bin/luajit-2.0.0, /usr/lib/libluajit-5.1.so.2.0.0
INSANE_SKIP_${PN}_pn-luajit = "already-stripped"
# /usr/lib/libx264.so.129
INSANE_SKIP_${PN}_pn-x264 = "textrel"
# Following entries for libav can be removed when oe-core is upgraded to 9a670f780b1f1204d426017ff9a95842ad85800e or newer
INSANE_SKIP_libswscale_pn-libav = "textrel"
INSANE_SKIP_libpostproc_pn-libav = "textrel"
INSANE_SKIP_libavutil_pn-libav = "textrel"
INSANE_SKIP_libavformat_pn-libav = "textrel"
INSANE_SKIP_libavfilter_pn-libav = "textrel"
INSANE_SKIP_libavdevice_pn-libav = "textrel"
INSANE_SKIP_libavcodec_pn-libav = "textrel"
# /usr/bin/oscam
INSANE_SKIP_${PN}_pn-oscam = "already-stripped"
