# (c) Copyright 2012  Hewlett-Packard Development Company, L.P. 

def qt4_machine_config_flags(bb, d):
    if bb.data.getVar('MACHINE', d, True):
        this_machine = bb.data.getVar('MACHINE', d, 1)

        if this_machine == "opal" or this_machine == "topaz":
            return "-xplatform qws/linux-armv6-g++ -opengl -plugin-gfx-egl -DQT_QWS_CLIENTBLIT -DPALM_DEVICE -qconfig palm"
        elif this_machine == "qemux86":
            return "-xplatform qws/linux-qemux86-g++ -no-neon -no-rpath -DPALM_DEVICE -qconfig palm"
        elif this_machine == "qemuarm":
            return "-xplatform qws/linux-armv6-g++ -no-opengl -DQT_QWS_CLIENTBLIT -no-neon -DPALM_DEVICE -qconfig palm"
        else:
            return "-xplatform qws/linux-armv6-g++ -DQT_QWS_CLIENTBLIT -no-neon -DPALM_DEVICE -qconfig palm"
    else:
        return ""

def qt4_machine_config_arch_lite(bb, d):
    if bb.data.getVar('MACHINE', d, True):
        this_machine = bb.data.getVar('MACHINE', d, 1)

        if this_machine == "qemux86":
            return "-embedded x86"
        elif this_machine == "qemuarm":
	    return "-arch arm -embedded"
        else:
            return "-arch arm -qpa"
    else:
        return ""
