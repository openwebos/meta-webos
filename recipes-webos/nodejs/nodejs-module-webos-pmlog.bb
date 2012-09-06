DESCRIPTION = "A module for nodejs that allows Javascript access to the Open webOS logging system"
LICENSE = "Apache-2.0"
SECTION = "webos/libs"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

inherit webos_component
inherit webos_public_repo
inherit webos_submissions
inherit webos_cmake

DEPENDS = "nodejs pmloglib"

WEBOS_GIT_TAG = "${WEBOS_SUBMISSION}"
SRC_URI = "${OPENWEBOS_GIT_REPO}/${PN};tag=${WEBOS_GIT_TAG};protocol=git"
S = "${WORKDIR}/git"

FILES_${PN} += " \
  ${libdir}/nodejs/webos-pmlog.node \
  ${libdir}/nodejs/pmloglib.node"
FILES_${PN}-dbg += "${libdir}/nodejs/.debug"
