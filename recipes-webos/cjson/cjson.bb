# (c) Copyright 2012  Hewlett-Packard Development Company, L.P. 

DESCRIPTION = "This repository contains the edition of the open-source json-c library used by webOS."
SECTION = "libs"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit autotools
inherit pkgconfig
inherit webos_enhanced_submissions

PR = "r1"

WEBOS_GIT_TAG = "submissions/${WEBOS_SUBMISSION}"
SRC_URI = "${OPENWEBOS_GIT_REPO}/${PN};tag=${WEBOS_GIT_TAG};protocol=git"
S = "${WORKDIR}/git"

EXTRA_OECONF += "--disable-static"
EXTRA_OEMAKE += "all"

do_configure_prepend() {
        # Force a configure to happen
        if [ -e ${S}/config.status ]; then
                rm -f ${S}/config.status
        fi
        sh autogen.sh
}

# XXX Perhaps this belongs in autotools.bbclass? (Is there always a maintainer-clean make target?)
#do_clean_append() {
#        s = bb.data.getVar('S', d, 1)
#        if os.path.exists(s + '/Makefile'):
#                bb.note('running make maintainer-clean')
#                os.system('make -C ' + s + ' -s maintainer-clean')
#        # do_configure() copies in the file ${HOST_PREFIX}libtool that "make maintainer-clean"
#        # doesn't remove, so do it manually:
#        if os.path.exists(s):
#                host_prefix = bb.data.getVar('HOST_PREFIX', d, 1)
#                bb.note('removing remaining files created by do_configure()')
#                os.system('cd ' + s + ' && rm -f ' + host_prefix + 'libtool')
#}

