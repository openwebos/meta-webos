pkg_postrm_modules () {
update-modules || true
}

autoload_postinst_fragment() {
if [ x"$D" = "x" ]; then
	modprobe %s || true
fi
}

do_install_append() {
    install -d ${D}${sysconfdir}/modules-load.d/ ${D}${sysconfdir}/modprobe.d/
}

python populate_packages_prepend () {
    def extract_modinfo(file):
        import tempfile, re, subprocess
        tempfile.tempdir = d.getVar("WORKDIR", True)
        tf = tempfile.mkstemp()
        tmpfile = tf[1]
        cmd = "PATH=\"%s\" %sobjcopy -j .modinfo -O binary %s %s" % (d.getVar("PATH", True), d.getVar("HOST_PREFIX", True) or "", file, tmpfile)
        subprocess.call(cmd, shell=True)
        f = open(tmpfile)
        l = f.read().split("\000")
        f.close()
        os.close(tf[0])
        os.unlink(tmpfile)
        exp = re.compile("([^=]+)=(.*)")
        vals = {}
        for i in l:
            m = exp.match(i)
            if not m:
                continue
            vals[m.group(1)] = m.group(2)
        return vals
    
    def parse_depmod():
        import re

        dvar = d.getVar('PKGD', True)
        if not dvar:
            bb.error("PKGD not defined")
            return

        kernelver = d.getVar('KERNEL_VERSION', True)
        kernelver_stripped = kernelver
        m = re.match('^(.*-hh.*)[\.\+].*$', kernelver)
        if m:
            kernelver_stripped = m.group(1)
        path = d.getVar("PATH", True)
        staging_kernel_dir = d.getVar("STAGING_KERNEL_DIR", True)
        system_map_file = "%s/boot/System.map-%s" % (dvar, kernelver)
        if not os.path.exists(system_map_file):
            system_map_file = "%s/System.map-%s" % (staging_kernel_dir, kernelver)
            if not os.path.exists(system_map_file):
                bb.fatal("System.map-%s does not exist in '%s/boot' nor STAGING_KERNEL_DIR '%s'" % (kernelver, dvar, staging_kernel_dir))

        cmd = "PATH=\"%s\" depmod -n -a -b %s -F %s %s" % (path, dvar, system_map_file, kernelver_stripped)
        f = os.popen(cmd, 'r')

        deps = {}
        pattern0 = "^(.*\.k?o):..*$"
        pattern1 = "^(.*\.k?o):\s*(.*\.k?o)\s*$"
        pattern2 = "^(.*\.k?o):\s*(.*\.k?o)\s*\\\$"
        pattern3 = "^\t(.*\.k?o)\s*\\\$"
        pattern4 = "^\t(.*\.k?o)\s*$"

        line = f.readline()
        while line:
            if not re.match(pattern0, line):
                line = f.readline()
                continue
            m1 = re.match(pattern1, line)
            if m1:
                deps[m1.group(1)] = m1.group(2).split()
            else:
                m2 = re.match(pattern2, line)
                if m2:
                    deps[m2.group(1)] = m2.group(2).split()
                    line = f.readline()
                    m3 = re.match(pattern3, line)
                    while m3:
                        deps[m2.group(1)].extend(m3.group(1).split())
                        line = f.readline()
                        m3 = re.match(pattern3, line)
                    m4 = re.match(pattern4, line)
                    deps[m2.group(1)].extend(m4.group(1).split())
            line = f.readline()
        f.close()
        return deps
    
    def get_dependencies(file, pattern, format):
        # file no longer includes PKGD
        file = file.replace(d.getVar('PKGD', True) or '', '', 1)
        # instead is prefixed with /lib/modules/${KERNEL_VERSION}
        file = file.replace("/lib/modules/%s/" % d.getVar('KERNEL_VERSION', True) or '', '', 1)

        if module_deps.has_key(file):
            import re
            dependencies = []
            for i in module_deps[file]:
                m = re.match(pattern, os.path.basename(i))
                if not m:
                    continue
                on = legitimize_package_name(m.group(1))
                dependency_pkg = format % on
                dependencies.append(dependency_pkg)
            return dependencies
        return []

    def frob_metadata(file, pkg, pattern, format, basename):
        import re
        vals = extract_modinfo(file)

        dvar = d.getVar('PKGD', True)

        # If autoloading is requested, output /etc/modules-load.d/<name>.conf and append
        # appropriate modprobe commands to the postinst
        autoload = d.getVar('module_autoload_%s' % basename, True)
        if autoload:
            name = '%s/etc/modules-load.d/%s.conf' % (dvar, basename)
            f = open(name, 'w')
            for m in autoload.split():
                f.write('%s\n' % m)
            f.close()
            postinst = d.getVar('pkg_postinst_%s' % pkg, True)
#            if not postinst:
#                bb.fatal("pkg_postinst_%s not defined" % pkg)
#            postinst += d.getVar('autoload_postinst_fragment', True) % autoload
#            d.setVar('pkg_postinst_%s' % pkg, postinst)

        # Write out any modconf fragment
        modconf = d.getVar('module_conf_%s' % basename, True)
        if modconf:
            name = '%s/etc/modprobe.d/%s.conf' % (dvar, basename)
            f = open(name, 'w')
            f.write("%s\n" % modconf)
            f.close()

        files = d.getVar('FILES_%s' % pkg, True)
        files = "%s /etc/modules-load.d/%s.conf /etc/modprobe.d/%s.conf" % (files, basename, basename)
        d.setVar('FILES_%s' % pkg, files)

        if vals.has_key("description"):
            old_desc = d.getVar('DESCRIPTION_' + pkg, True) or ""
            d.setVar('DESCRIPTION_' + pkg, old_desc + "; " + vals["description"])

        rdepends = bb.utils.explode_dep_versions2(d.getVar('RDEPENDS_' + pkg, True) or "")
        for dep in get_dependencies(file, pattern, format):
            if not dep in rdepends:
                rdepends[dep] = []
        d.setVar('RDEPENDS_' + pkg, bb.utils.join_deps(rdepends, commasep=False))

    module_deps = parse_depmod()
    module_regex = '^(.*)\.k?o$'
    module_pattern = 'kernel-module-%s'

    postinst = d.getVar('pkg_postinst_modules', True)
    postrm = d.getVar('pkg_postrm_modules', True)
    modules = do_split_packages(d, root='/lib/modules', file_regex=module_regex, output_pattern=module_pattern, description='%s kernel module', postinst=postinst, postrm=postrm, recursive=True, hook=frob_metadata, extra_depends='update-modules kernel-%s' % d.getVar("KERNEL_VERSION", True))
    if modules:
        d.prependVar("PACKAGES", modules)

    # If modules-load.d and modprobe.d are empty at this point, remove them to
    # avoid warnings. removedirs only raises an OSError if an empty
    # directory cannot be removed.
    dvar = d.getVar('PKGD', True)
    for dir in ["%s/etc/modprobe.d" % (dvar), "%s/etc/modules-load.d" % (dvar), "%s/etc" % (dvar)]:
        if len(os.listdir(dir)) == 0:
            os.rmdir(dir)
}
