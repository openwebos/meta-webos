# Imported from meta-mentor
# http://git.yoctoproject.org/cgit/cgit.cgi/meta-mentor/log/classes/buildstats-summary.bbclass
# with following modifications
# 1) when both setscene and normal tasks were executed, count it as no_sstate
# 2) count do_populate_sysroot and do_packagedata tasks, because often we invalidate only runtime deps

python buildstats_summary () {
    if not isinstance(e, bb.event.BuildCompleted):
        return

    import os.path

    bn = get_bn(e)
    bsdir = os.path.join(e.data.getVar('BUILDSTATS_BASE', True), bn)
    if not os.path.exists(bsdir):
        return

    sstate_ps, no_sstate_ps, sstate_pd, no_sstate_pd = set(), set(), set(), set()
    for pf in os.listdir(bsdir):
        taskdir = os.path.join(bsdir, pf)
        if not os.path.isdir(taskdir):
            continue

        tasks = os.listdir(taskdir)
        if 'do_populate_sysroot' in tasks:
            no_sstate_ps.add(pf)
        elif 'do_populate_sysroot_setscene' in tasks:
            sstate_ps.add(pf)
        if 'do_packagedata' in tasks:
            no_sstate_pd.add(pf)
        elif 'do_packagedata_setscene' in tasks:
            sstate_pd.add(pf)

    if not sstate_ps and not no_sstate_ps and not sstate_pd and not no_sstate_pd:
        return

    bb.note("Build completion summary: (populate_sysroot/packagedata)")
    bb.note("  From shared state: %d/%d" % (len(sstate_ps), len(sstate_pd)))
    bb.note("  From scratch: %d/%d" % (len(no_sstate_ps), len(no_sstate_ps)))
}
addhandler buildstats_summary
