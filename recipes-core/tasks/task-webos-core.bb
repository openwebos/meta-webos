#
# (c) Copyright 2012  Hewlett-Packard Development Company, L.P. 
#

DESCRIPTION = "Test apps task for WebOS"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

PACKAGES = "\
    ${PN} \
    ${PN}-dbg \
    ${PN}-dev \
    "

PACKAGE_ARCH = "${MACHINE_ARCH}"

ALLOW_EMPTY = "1"

RDEPENDS_${PN} = " \
     base-files \
     base-passwd \  
     bash \
     binutils \
     boost \
     busybox \
     bzip2 \
     c-ares \
     db \
     e2fsprogs \	  		
     freetype \
     giflib \
     glib-2.0 \
     glibmm \
     icu \
     initscripts \        
     jpeg \
     libgcrypt \
     libgpg-error \
     libpng \
     libtool \
     libxml2 \
     libxslt \
     makedevs \
     mjson \
     module-init-tools \
     ncurses \
     openssl \
     orc \
     parted \
     readline \
     sqlite3 \
     sysvinit \
     sysvinit-pidof \
     udev \
     update-modules \
     update-rc.d \
     upstart \
     upstart-sysvcompat \
     yajl \
     zlib \
     psmisc \
     tzdata \
     hunspell \
     iproute2 \
     netbase \
     dropbear \
     gzip \
     dhcp-client \
     procps \
     modutils-initscripts \
     bluez4 \
     connman \
     ltp \
     uriparser \
     libtinyxml \
     opkg \
     ptmalloc3 \
"

DEPENDS_${PN} = " \
     intltool-native \
     genext2fs-native \
     pkgconfig-native \
"

