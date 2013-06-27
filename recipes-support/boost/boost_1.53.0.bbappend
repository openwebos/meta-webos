# Copyright (c) 2013 LG Electronics, Inc.

# Remove this when upgrading to oe-core with
# http://lists.openembedded.org/pipermail/openembedded-core/2013-June/080344.html
# included

PR_append = "webos1"

# use PARALLEL_MAKE to speed up the build, but limit it by -j 64, greater paralelism causes bjam to segfault or to ignore -j
# https://svn.boost.org/trac/boost/ticket/7634
def get_boost_parallel_make(bb, d):
    pm = d.getVar('PARALLEL_MAKE', True)
    if pm:
        # people are usually using "-jN" or "-j N", but let it work with something else appended to it
        import re
        pm_prefix = re.search("\D+", pm)
        pm_val = re.search("\d+", pm)
        if pm_prefix is None or pm_val is None:
            bb.error("Unable to analyse format of PARALLEL_MAKE variable: %s" % pm)
        pm_nval = min(64, int(pm_val.group(0)))
        return pm_prefix.group(0) + str(pm_nval) + pm[pm_val.end():]

BOOST_PARALLEL_MAKE = "${@get_boost_parallel_make(bb, d)}"
BJAM_OPTS    = '${BOOST_PARALLEL_MAKE} \
                ${BJAM_TOOLS} \
                -sBOOST_BUILD_USER_CONFIG=${S}/tools/build/v2/user-config.jam \
                --builddir=${S}/${TARGET_SYS} \
                --disable-icu \
                ${BJAM_EXTRA}'
