# (c) Copyright 2012  Hewlett-Packard Development Company, L.P. 

DESCRIPTION = "a simple, small, minimal, C++ XML parser"
HOMEPAGE = "http://www.sourceforge.net/projects/tinyxml"
LICENSE = "zlib"
LIC_FILES_CHKSUM = "file://readme.txt;md5=f8f366f3370dda889f60faa7db162cf4"
SECTION = "libs"

PR = "r3"

SRC_URI = "${SOURCEFORGE_MIRROR}/tinyxml/tinyxml_2_6_2.tar.gz"

S = "${WORKDIR}/tinyxml"

do_compile() {
    ${CXX} ${CXXFLAGS} -I${S} -c -o ${S}/tinyxml.o ${S}/tinyxml.cpp
    ${CXX} ${CXXFLAGS} -I${S} -c -o ${S}/tinyxmlerror.o ${S}/tinyxmlerror.cpp
    ${CXX} ${CXXFLAGS} -I${S} -c -o ${S}/tinyxmlparser.o ${S}/tinyxmlparser.cpp
    ${CXX} ${CXXFLAGS} \
            -shared \
            -Wl,-soname,libtinyxml.so.${PV} \
            -o ${S}/libtinyxml.so.${PV} \
            ${LDFLAGS} \
            ${S}/tinyxml.o \
            ${S}/tinyxmlparser.o \
            ${S}/tinyxmlerror.o
}

do_install() {
    install -d ${D}${libdir}
    install -m 0755 ${S}/libtinyxml.so.${PV} ${D}${libdir}
    ln -sf libtinyxml.so.${PV} ${D}${libdir}/libtinyxml.so

    install -d ${D}${includedir}
    install -m 0644 ${S}/tinyxml.h ${D}${includedir}
}

SRC_URI[md5sum] = "c1b864c96804a10526540c664ade67f0"
SRC_URI[sha256sum] = "15bdfdcec58a7da30adc87ac2b078e4417dbe5392f3afb719f9ba6d062645593"

