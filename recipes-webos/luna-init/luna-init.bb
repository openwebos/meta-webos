# (c) Copyright 2012  Hewlett-Packard Development Company, L.P. 

DESCRIPTION = "Luna Init"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"
SECTION = "None"

PROVIDES = "luna-init"

inherit webos_component
inherit webos_public_repo
inherit webos_submissions
inherit webos_cmake

PR = "${WEBOS_SUBMISSION}"
SRC_URI = "${OPENWEBOS_GIT_REPO}/${PN};tag=${PR};protocol=git"
S="${WORKDIR}/git"

FILES_${PN} += "/usr/palm /etc/palm /usr/share/fonts"

