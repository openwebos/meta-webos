# (c) Copyright 2012  Hewlett-Packard Development Company, L.P.

# XXX Revisit this .bbappend once we're using an openembedded-core with
# 6a4f394bc1280f5d58d928a2f7cff7cce4eb3b2b (applied to gnomebase.bbclass on
# 2012-11-02).

# Override the unneeded dependencies set via the inherit of gnome.bbclass done
# in the original recipe.
DEPENDS = ""
RDEPENDS_${PN} = ""

PR_append = "webos1"

# All the original recipe does is stage a tarball and some autotools files;
# nothing compiled.
inherit allarch

# Nothing in mm-common is installed on the target => the base package should
# be empty. What's below works because ${PN}-dev is packaged ahead of ${PN}.
FILES_${PN}-dev += "${FILES_${PN}}"
