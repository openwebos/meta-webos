# (c) Copyright 2012-2013 Hewlett-Packard Development Company, L.P.
# (c) Copyright 2013 LG Electronics

# Keep in sync with setting in oe-core/meta/conf/distro/include/default-distrovars.inc
# TODO: Maybe change this to use "_remove" when it's introduced in bitbake

# Split some features to separate variables
WEBOS_DISTRO_FEATURES_DEFAULT = "\
  alsa argp ext2 irda largefile xattr \
"
WEBOS_DISTRO_FEATURES_OPENGL = "opengl"
WEBOS_DISTRO_FEATURES_WINDOW_SYSTEM = "x11"
WEBOS_DISTRO_FEATURES_BUSES = "pci pcmcia"
WEBOS_DISTRO_FEATURES_USB = "usbhost usbgadget"
WEBOS_DISTRO_FEATURES_CONNECTIVITY = "bluetooth 3g wifi"
WEBOS_DISTRO_FEATURES_NETWORKING = "nfs zeroconf"

DISTRO_FEATURES = "\
  ${WEBOS_DISTRO_FEATURES_DEFAULT} \
  ${WEBOS_DISTRO_FEATURES_OPENGL} \
  ${WEBOS_DISTRO_FEATURES_WINDOW_SYSTEM} \
  ${WEBOS_DISTRO_FEATURES_BUSES} \
  ${WEBOS_DISTRO_FEATURES_USB} \
  ${WEBOS_DISTRO_FEATURES_CONNECTIVITY} \
  ${WEBOS_DISTRO_FEATURES_NETWORKING} \
  ${WEBOS_DISTRO_FEATURES_LIBC} \
"
