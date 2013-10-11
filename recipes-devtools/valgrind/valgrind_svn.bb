DESCRIPTION = "Valgrind memory debugger"
HOMEPAGE = "http://valgrind.org/"
BUGTRACKER = "http://valgrind.org/support/bug_reports.html"
LICENSE = "GPLv2 & GPLv2+ & BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=c46082167a314d785d012a244748d803 \
                    file://include/pub_tool_basics.h;beginline=1;endline=29;md5=6b18ba0139d10678ce3a9969f68e4c6d \
                    file://include/valgrind.h;beginline=1;endline=56;md5=b6bb5ab625a759823e17197ec3e2ee83 \
                    file://COPYING.DOCS;md5=8fdeb5abdb235a08e76835f8f3260215"

X11DEPENDS = "virtual/libx11"
DEPENDS = "${@base_contains('DISTRO_FEATURES', 'x11', '${X11DEPENDS}', '', d)}"

PR = "r1"
SRCREV = "13429"
PV = "3.8.1+svnr${SRCPV}"

SRC_URI = "svn://svn.valgrind.org/valgrind;module=trunk;rev=${SRCREV};protocol=svn \
           file://fixed-perl-path.patch \
           file://sepbuildfix.patch \
          "
S = "${WORKDIR}/trunk"

COMPATIBLE_HOST = '(i.86|x86_64|powerpc|powerpc64).*-linux'
COMPATIBLE_HOST_armv7a = 'arm.*-linux'

inherit autotools

EXTRA_OECONF = "--enable-tls --without-mpicc"
EXTRA_OECONF_armv7a = "--enable-tls -host=armv7-none-linux-gnueabi --without-mpicc"
EXTRA_OEMAKE = "-w"
PARALLEL_MAKE = ""

do_install_append () {
    install -m 644 ${B}/default.supp ${D}/${libdir}/valgrind/
}

FILES_${PN}-dbg += "${libdir}/${PN}/*/.debug/*"
RRECOMMENDS_${PN}_powerpc += "${TCLIBC}-dbg"
RRECOMMENDS_${PN}_powerpc64 += "${TCLIBC}-dbg"
RRECOMMENDS_${PN}_armv7a += "${TCLIBC}-dbg"
RRECOMMENDS_${PN}_x86 += "${TCLIBC}-dbg"
