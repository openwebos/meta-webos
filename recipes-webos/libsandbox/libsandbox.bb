# (c) Copyright 2012  Hewlett-Packard Development Company, L.P. 

#EXTRA_OECMAKE += " -DWEBOS_INSTALL_LIBDIR:PATH=${D}${libdir}"

#EXTRA_OEMAKE += "<additional arguments to make>"

DESCRIPTION = "libsandbox"
LICENSE = "Apache-2.0"
SECTION = "webos/libs"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

inherit webos_component
inherit webos_public_repo
inherit webos_enhanced_submissions
inherit webos_cmake
inherit webos_machine_impl_dep
inherit webos_library

WEBOS_GIT_TAG = "submissions/${WEBOS_SUBMISSION}"
SRC_URI = "${OPENWEBOS_GIT_REPO}/${PN};tag=${WEBOS_GIT_TAG};protocol=git"
S = "${WORKDIR}/git"

ALLOW_EMPTY_${PN} = "1"


