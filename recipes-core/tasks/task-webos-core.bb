# (c) Copyright 2012  Hewlett-Packard Development Company, L.P. 

DESCRIPTION = "openembedded-core and meta-oe components used in Open webOS"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

PR = "r4"

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
     curl \
     db \
     dhcp-client \
     dropbear \
     e2fsprogs \
     freetype \
     giflib \
     glib-2.0 \
     glibmm \
     gzip \
     hunspell \
     icu \
     iproute2 \
     jpeg \
     libgcrypt \
     libgpg-error \
     libpng \
     libtinyxml \
     libtool \
     libxml2 \
     libxslt \
     makedevs \
     module-init-tools \
     modutils-initscripts \
     ncurses \
     netbase \
     openssl \
     opkg \
     orc \
     parted \
     procps \
     psmisc \
     ptmalloc3 \
     readline \
     sqlite3 \
     sysvinit-pidof \
     tzdata \
     update-modules \
     update-rc.d \
     upstart-sysvcompat \
     uriparser \
     yajl \
     zlib \
"
