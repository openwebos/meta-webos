# Copyright (c) 2014 LG Electronics, Inc.

# Exclude these packages from ICECC build due to build failures

ICECC_USER_PACKAGE_BL_append = " qt4-webos \
                               "

ICECC_USER_PACKAGE_BL_append_arm = " busybox \
                                     e2fsprogs \
                                     gmp \
                                     libcap \
                                     libgpg-error \
                                     ncurses \
                                   "
