# Copyright (c) 2012 Hewlett-Packard Development Company, L.P.

SECTION = "libs"
DESCRIPTION = "Multi-thread malloc implementation"
HOMEPAGE = "http://www.malloc.de/en/"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD;md5=3775480a712fc46a69647678acb234cb"

PR = "r1"

SRC_URI = "http://www.malloc.de/malloc/ptmalloc3-current.tar.gz \
           file://ptmalloc3-current-webos.patch "

S = "${WORKDIR}/${PN}"

do_compile () {
    cd ${S}; make -f Makefile.palm CC="$CC"
}

FILES_${PN} = " ${libdir}/lib*.so"
FILES_${PN}-dev = "${includedir}"

do_install() {
    #oenote instaling ptmalloc3
    install -d ${D}${libdir}
    install -d ${D}${includedir}
    install -m 555 -p ${S}/libptmalloc3.so    ${D}${libdir}
    install -m 666 ${S}/mmap_dev_heap.h ${D}/${includedir}
    install -m 666 ${S}/malloc-2.8.3.h  ${D}/${includedir}
    install -m 666 ${S}/malloc_utils.h  ${D}/${includedir}
    cp -a   ${S}/sysdeps         ${D}/${includedir}
    rm ${D}/${includedir}/sysdeps/generic/atomic.h
}

SRC_URI[md5sum] = "c0b9dd5f16f8eae979166dc74b60015c"
SRC_URI[sha256sum] = "f353606f24a579597a1ff5b51009a45d75da047b3975d82c3f613f85bcf312db"

