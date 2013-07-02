# Copyright (c) 2013 LG Electronics, Inc.

PR_append = "webos1"

# Remove the unneeded dependency. This is also removed from upstream OE
# http://git.yoctoproject.org/cgit/cgit.cgi/poky/commit/meta/recipes-graphics?id=2c5dbc9291b4c77457a5f7d5ea96f586acf7f902
RDEPENDS_${PN} = ""

