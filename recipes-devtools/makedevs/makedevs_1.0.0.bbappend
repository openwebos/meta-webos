FILESEXTRAPATHS_prepend := "${THISDIR}/${BP}:"

PKGV .= "-0webos1"

# First patch is from oe-core after 1.5 (Dora) release
# Second patch is fix for not setting permissions on existing device nodes
SRC_URI += " \
    file://0001-makedevs-several-fixes.patch \
    file://0002-makedevs-Add-trace-option-and-fix-permissions-on-fil.patch \
"

# Add ${LDFLAGS}
do_compile() {
    ${CC} ${CFLAGS} ${LDFLAGS} -o ${S}/makedevs ${S}/makedevs.c
}
