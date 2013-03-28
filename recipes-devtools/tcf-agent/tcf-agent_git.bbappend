# Copyright (c) 2013 LG Electronics

PR_append = "webos1"

# Remove epl-v10.html from SRC_URI and LIC_FILES_CHKSUM
# Revisit this bbappend when oe-core is upgraded to include
# http://lists.linuxtogo.org/pipermail/openembedded-core/2013-March/037675.html

LIC_FILES_CHKSUM = "file://edl-v10.html;md5=522a390a83dc186513f0500543ad3679"

SRC_URI = "git://git.eclipse.org/gitroot/tcf/org.eclipse.tcf.agent.git;protocol=git \
           file://fix_ranlib.patch \
           file://fix_tcf-agent.init.patch \
          "
