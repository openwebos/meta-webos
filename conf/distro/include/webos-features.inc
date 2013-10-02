# Copyright (c) 2012-2013 LG Electronics, Inc.

# Keep in sync with setting in oe-core/meta/conf/distro/include/default-distrovars.inc
# TODO: Maybe change this to use "_remove" when it's introduced in bitbake

# Split some features to separate variables
# Remove argp (not used in OE anymore) and x11 (not needed for Open webOS)
WEBOS_DISTRO_FEATURES_DEFAULT = "\
  alsa ext2 irda largefile xattr \
"
WEBOS_DISTRO_FEATURES_OPENGL = "opengl"
# Remove x11
WEBOS_DISTRO_FEATURES_WINDOW_SYSTEM = ""
WEBOS_DISTRO_FEATURES_BUSES = "pci pcmcia"
WEBOS_DISTRO_FEATURES_USB = "usbhost usbgadget"
WEBOS_DISTRO_FEATURES_CONNECTIVITY = "bluetooth 3g wifi"
WEBOS_DISTRO_FEATURES_NETWORKING = "nfs zeroconf"

WEBOS_DISTRO_FEATURES_INIT = "upstart sysvinit"
WEBOS_DISTRO_FEATURES_INIT_CONSIDERED = "sysvinit"
DISTRO_FEATURES_BACKFILL_CONSIDERED += "${WEBOS_DISTRO_FEATURES_INIT_CONSIDERED}"
WEBOS_DISTRO_FEATURES_DISABLE_LS2_SECURITY = "disable_ls2_security"

DISTRO_FEATURES = "\
  ${WEBOS_DISTRO_FEATURES_DEFAULT} \
  ${WEBOS_DISTRO_FEATURES_OPENGL} \
  ${WEBOS_DISTRO_FEATURES_WINDOW_SYSTEM} \
  ${WEBOS_DISTRO_FEATURES_BUSES} \
  ${WEBOS_DISTRO_FEATURES_USB} \
  ${WEBOS_DISTRO_FEATURES_CONNECTIVITY} \
  ${WEBOS_DISTRO_FEATURES_NETWORKING} \
  ${WEBOS_DISTRO_FEATURES_LIBC} \
  ${WEBOS_DISTRO_FEATURES_INIT} \
  ${WEBOS_DISTRO_FEATURES_DISABLE_LS2_SECURITY} \
"
