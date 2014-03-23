DESCRIPTION = "tzcode, timezone zoneinfo utils -- zic, zdump, tzselect"
LICENSE = "PD"

LIC_FILES_CHKSUM = "file://${WORKDIR}/README;md5=0b7570113550eb5d30aa4bd220964b8f"

# note that we allow for us to use data later than our code version
#
SRC_URI =" ftp://ftp.iana.org/tz/releases/tzcode${PV}.tar.gz;name=tzcode \
           ftp://ftp.iana.org/tz/releases/tzdata2014a.tar.gz;name=tzdata"


SRC_URI[tzcode.md5sum] = "77ccbb720f0f2076f12dff6ded70eb98"
SRC_URI[tzcode.sha256sum] = "05b93ba541b167a4c10f2e81a7baf972c24ff12db27d85f6c2dd328443c4d3f5"
SRC_URI[tzdata.md5sum] = "423a11bcffc10dda578058cf1587d048"
SRC_URI[tzdata.sha256sum] = "7cff254ce85e11b21c994b284bccd1e12ecda9dadf947fbb32e1912fd520e8b1"

S = "${WORKDIR}"

inherit native

do_install () {
        install -d ${D}${bindir}/
        install -m 755 zic ${D}${bindir}/
        install -m 755 zdump ${D}${bindir}/
        install -m 755 tzselect ${D}${bindir}/
}
