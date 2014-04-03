# Copyright (c) 2014 LG Electronics, Inc.

# These packages fail to build when icecc is used

ICECC_USER_PACKAGE_BL_append = " \
    qt4-webos \
"

ICECC_USER_PACKAGE_BL_append_arm = " \
    busybox \
    e2fsprogs \
    gcc \
    gmp \
    libcap \
    libgpg-error \
    linux-libc-headers \
    ncurses \
    perl \
"
