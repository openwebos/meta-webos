# Version 4 of the Berkeley DB from Sleepycat
#
# At present this package only installs the DB code
# itself (shared libraries, .a in the dev package),
# documentation and headers.
#
# The headers have the same names as those as v3
# of the DB, only one version can be used *for dev*
# at once - DB3 and DB4 can both be installed on the
# same system at the same time if really necessary.
SECTION = "libs"
DESCRIPTION = "Berkeley DB v4."
HOMEPAGE = "http://www.sleepycat.com"
LICENSE = "Sleepycat"
VIRTUAL_NAME ?= "virtual/db"
RCONFLICTS_${PN} = "db3"
PR = "r1"

SRC_URI = "http://download.oracle.com/berkeley-db/db-${PV}.tar.gz"
SRC_URI[md5sum] = "f80022099c5742cd179343556179aa8c"
SRC_URI[sha256sum] = "e0491a07cdb21fb9aa82773bbbedaeb7639cbd0e7f96147ab46141e0045db72a"

LIC_FILES_CHKSUM = "file://../LICENSE;md5=1e17379fdf7e95628f4bc9f649d504a9"

inherit autotools

# Put virtual/db in any appropriate provider of a
# relational database, use it as a dependency in
# place of a specific db and use:
#
# PREFERRED_PROVIDER_virtual/db
#
# to select the correct db in the build (distro) .conf
PROVIDES += "${VIRTUAL_NAME}"

# The executables go in a separate package - typically there
# # is no need to install these unless doing real database
# # management on the system.
inherit lib_package

# bitbake isn't quite clever enough to deal with sleepycat,
# the distribution sits in the expected directory, but all
# the builds must occur from a sub-directory.  The following
# persuades bitbake to go to the right place
S = "${WORKDIR}/db-${PV}/dist"
B = "${WORKDIR}/db-${PV}/build_unix"

# The dev package has the .so link (as in db3) and the .a's -
# # it is therefore incompatible (cannot be installed at the
# # same time) as the db3 package
# # sort out the .so since they do version prior to the .so
SOLIBS = "-4*.so"
FILES_SOLIBSDEV = "${libdir}/libdb.so"

#configuration - set in local.conf to override
EXTRA_OECONF = "--disable-largefile --disable-cryptography --disable-hash --disable-queue --disable-replication --enable-o_direct --disable-static"

do_configure() {
	export STRIP="true"
	oe_runconf
}

do_configure_append() {
	# skip docs to eliminate warnings about installed but unpackaged files.
	sed -i '/^library_install:/s/ install_docs//' ${B}/Makefile
}

INSANE_SKIP_${PN} = "dev-so"
