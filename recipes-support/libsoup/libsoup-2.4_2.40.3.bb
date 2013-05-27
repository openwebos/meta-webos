DESCRIPTION = "An HTTP library implementation in C"
HOMEPAGE = "http://www.gnome.org/"
BUGTRACKER = "https://bugzilla.gnome.org/"

LICENSE = "LGPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=5f30f0716dfdd0d91eb439ebec522ec2"

SECTION = "x11/gnome/libs"

DEPENDS = "glib-2.0 gnutls libxml2 libproxy"

PACKAGECONFIG ??= "${@base_contains('DISTRO_FEATURES', 'x11', 'gnome', '', d)}"
PACKAGECONFIG[gnome] = "--with-gnome,--without-gnome,libgnome-keyring sqlite3"

SHRT_VER = "${@bb.data.getVar('PV',d,1).split('.')[0]}.${@bb.data.getVar('PV',d,1).split('.')[1]}"
SRC_URI = "${GNOME_MIRROR}/libsoup/${SHRT_VER}/libsoup-${PV}.tar.xz"

SRC_URI[md5sum] = "ee111c5cf9f95b4a7aa24afe13dddb98"
SRC_URI[sha256sum] = "82c92f1f6f4cbfd501df783ed87e7de9410b4a12a3bb0b19c64722e185d2bbc9"

S = "${WORKDIR}/libsoup-${PV}"

inherit autotools pkgconfig

# glib-networking is needed for SSL, proxies, etc.
RRECOMMENDS_${PN} = "glib-networking"
