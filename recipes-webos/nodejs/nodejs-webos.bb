DESCRIPTION = "Open webOS edition of the server-side JavaScript environment that uses a \
asynchronous event-driven model."
LICENSE = "MIT & Zlib"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d6237f3a840aef5b7880fb4e49eecfe5"

DEPENDS = "openssl"
PROVIDES = "nodejs"

inherit pythonnative
inherit webos_component
inherit webos_public_repo
inherit webos_submissions
inherit webos_cmake

WEBOS_GIT_TAG = "${WEBOS_SUBMISSION}"
SRC_URI = "${OPENWEBOS_GIT_REPO}/nodejs;tag=${WEBOS_GIT_TAG};protocol=git"
S = "${WORKDIR}/git"
