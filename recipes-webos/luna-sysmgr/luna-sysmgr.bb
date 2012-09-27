# (c) Copyright 2010 - 2012  Hewlett-Packard Development Company, L.P.

DESCRIPTION = "Open webOS System Manager"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"
SECTION = "webos/base"

DEPENDS = "cjson luna-service2 sqlite3 luna-sysmgr-ipc luna-sysmgr-ipc-messages pmloglib qt4-webos librolegen nyx-lib openssl luna-webkit-api webkit-webos luna-prefs libpbnjson npapi-headers freetype"
#DEPENDS += "localization serviceinstaller" #TODO

# luna-sysmgr's upstart conf expects to be able to LD_PRELOAD ptmalloc3
RDEPENDS_${PN} = "ptmalloc3"
# luna-sysmgr's upstart conf expects to have ionice available. Under OE-core, this is supplied by util-linux.
RDEPENDS_${PN} += "util-linux"
#RDEPENDS_${PN} += "jail" #TODO

PR = "r3"

# Don't uncomment until all of the do_*() tasks have been moved out of the recipe
#inherit webos_component
inherit webos_public_repo
inherit webos_submissions
inherit webos_system_bus
# Uncomment once installing into /usr/sbin instead of /usr/bin
#inherit webos_daemon
inherit webos_machine_dep

WEBOS_GIT_TAG = "${WEBOS_SUBMISSION}"
SRC_URI = "${OPENWEBOS_GIT_REPO}/${PN};tag=${WEBOS_GIT_TAG};protocol=git"
S = "${WORKDIR}/git"

EXTRA_OEMAKE = " MAKEFLAGS= "

export STRIP_TMP="${STRIP}"
export F77_TMP="${F77}"
export QMAKE_MKSPEC_PATH_TMP="${QMAKE_MKSPEC_PATH}"
export CC_TMP="${CC}"
export CPPFLAGS_TMP="${CPPFLAGS}"
export RANLIB_TMP="${RANLIB}"
export CXX_TMP="${CXX}"
export OBJCOPY_TMP="${OBJCOPY}"
export CCLD_TMP="${CCLD}"
export CFLAGS_TMP="${CFLAGS}"
export TARGET_LDFLAGS_TMP="${TARGET_LDFLAGS}"
export LDFLAGS_TMP="${LDFLAGS}"
export AS_TMP="${AS}"
export AR_TMP="${AR}"
export CPP_TMP="${CPP}"
export TARGET_CPPFLAGS_TMP="${TARGET_CPPFLAGS}"
export CXXFLAGS_TMP="${CXXFLAGS}"
export OBJDUMP_TMP="${OBJDUMP}"
export LD_TMP="${LD}"

do_configure() {
	export STAGING_INCDIR="${STAGING_INCDIR}"
	export STAGING_LIBDIR="${STAGING_LIBDIR}"
	export MACHINE=${MACHINE}
	${STAGING_BINDIR_NATIVE}/qmake-palm
}

do_compile_prepend() {
	export STAGING_INCDIR="${STAGING_INCDIR}"
	export STAGING_LIBDIR="${STAGING_LIBDIR}"
	export MACHINE=${MACHINE}
}

#install_loc() {
#	# generate all the localized files in the resources directory
#	COMPONENT_NAME=luna-sysmgr
#	${STAGING_DIR}/xliffs/tool/buildloc ${COMPONENT_NAME} ${STAGING_DIR}/xliffs $(echo ${LOCALES} | tr " " ,)
#	if [ -f ${COMPONENT_NAME}_newstrings.xliff ]
#	then
#		mv -f *.xliff ${STAGING_DIR}/xliffs/new
#	fi

#	tar --exclude=.svn -cf - localization | tar xf - -C ${D}/usr/palm/sysmgr
#	#ln -s en_gb ${D}/usr/palm/sysmgr/localization/en_ie
#	if [ -d ${D}/usr/palm/sysmgr/localization/es_mx ]
#	then
#		rm -rf ${D}/usr/palm/sysmgr/localization/es_mx
#	fi
#	ln -sf es_us ${D}/usr/palm/sysmgr/localization/es_mx
#}

install_launcher3_support() {

	install -d ${D}/${sysconfdir}/palm/launcher3/
	# install all the base conf files
	cd ${S}
	if [ -d conf/launcher3 ]
	then
	  find conf/launcher3/ -maxdepth 1 -name "*.conf" -not -name "*[-]*.conf" -print0 | xargs -0 -I file install -m 644 file ${D}/${sysconfdir}/palm/launcher3/
	fi
	
	# and all the platform specific conf files
	# (no good way to do this with find/xargs, given the <base>-<machine>.conf -> <base>-platform.conf name change needed on the install copy)
	# (do them all individually)
	
	#install the default designator mapping and tab/page definition - default is in /etc/palm/launcher3/app-keywords-to-designator-map.txt
	if [ -f conf/launcher3/app-keywords-to-designator-map.txt ]
	then
		install -m 644 conf/launcher3/app-keywords-to-designator-map.txt ${D}/${sysconfdir}/palm/launcher3/
	fi
	
}

do_install() {
        oe_runmake install
#	bbnote "Installing luna-sysmgr"
	install -d ${D}${bindir}
	install -m 750 release-${MACHINE}/LunaSysMgr ${D}${bindir}

	# install images & low-memory files
#	bbnote "install images and low-memory files"
	install -d ${D}/usr/palm/sysmgr
	cd ${S} && tar --exclude=.svn -cf - images | tar xf - -C ${D}/usr/palm/sysmgr
	cd ${S} && tar --exclude=.svn -cf - uiComponents | tar xf - -C ${D}/usr/palm/sysmgr
	install -d ${D}/usr/palm/sysmgr/low-memory
	install -m 644 low-memory/* ${D}/usr/palm/sysmgr/low-memory

	if [ -d bin ]
	then
#		bbnote "install ime bin files"
		install -d ${D}/usr/palm/sysmgr/bin
		install -m 644 bin/* ${D}/usr/palm/sysmgr/bin
	fi
	
	# install sysmgr builtins apps
#	bbnote "install sysmgr builtins apps"
	if [ -d sysapps ]
	then
		
	#	for sysappDir in $( ls ${S}/sysapps )
	#	do	
			#Now install the localization json files
			# generate all the localized files in the resources directory
	#		cd ${S}/sysapps/${sysappDir}
	#		${STAGING_DIR}/xliffs/tool/buildloc -e luna-sysmgr ${STAGING_DIR}/xliffs $(echo ${LOCALES} | tr " " ,)
			
			#Workaround the luna-sysmgr 'workaround', basically, revert to normal behavior
	#		if [ -d localization ]
	#		then
	#			mv -f localization resources
	#		fi
	#		find ./ -name strings.json | xargs rm -rf
	#	done

		
		install -d ${D}/usr/palm/applications
		cd ${S}/sysapps && tar --exclude=.svn -cf - * | tar xf - -C ${D}/usr/palm/applications
		cd ${S}
		
	fi
	
	# Install launcher things
#	bbnote "Install launcher things"
	
	install_launcher3_support
	
	# install sysmgr builtins apps
#	bbnote "install sysmgr builtins apps"
	cd ${S}
	if [ -d sysapps ]
	then
		install -d ${D}/usr/palm/applications
		cd ${S}/sysapps && tar --exclude=.svn -cf - * | tar xf - -C ${D}/usr/palm/applications
		cd ${S}
	fi

	# install localization
	#install_loc

	# install the schema files
	install -d ${D}${sysconfdir}/palm/schemas/
	#install -m 644 conf/localization.schema ${D}${sysconfdir}/palm/schemas/
	install -m 644 conf/launcher-conf.schema ${D}${sysconfdir}/palm/schemas/

	# install temporary sounds
	install -d ${D}/usr/palm/sounds
	install -m 644 sounds/* ${D}/usr/palm/sounds

	# install into event.d so we run.
	install -d ${D}${sysconfdir}/event.d
	install -m 644 LunaSysMgr.upstart ${D}${sysconfdir}/event.d/LunaSysMgr
	install -m 644 LunaReady.upstart ${D}${sysconfdir}/event.d/LunaReady

	# install the luna.conf file if it exists in the source
	if [ -f conf/luna.conf ]
	then
		install -d ${D}${sysconfdir}/palm
		install -m 644 conf/luna.conf ${D}${sysconfdir}/palm
		install -m 644 conf/lunaAnimations.conf ${D}${sysconfdir}/palm
		install -m 644 conf/timezone.txt ${D}${sysconfdir}/palm
		install -m 644 conf/locale.txt ${D}${sysconfdir}/palm
		install -m 644 conf/defaultPreferences.txt ${D}${sysconfdir}/palm
		install -m 644 conf/notificationPolicy.conf ${D}${sysconfdir}/palm
		install -m 644 conf/persistentWindows.conf ${D}${sysconfdir}/palm
		install -m 644 conf/default-launcher-page-layout.json ${D}${sysconfdir}/palm
	fi

	# install the platform luna.conf file
#	bbnote "install the platform luna.conf file"
	if [ -f conf/luna-${MACHINE}.conf ]
	then
		install	-m 644 conf/luna-${MACHINE}.conf ${D}${sysconfdir}/palm/luna-platform.conf
	fi

	# install the platform lunaAnimations.conf file
	if [ -f conf/lunaAnimations-${MACHINE}.conf ]
	then
		install	-m 644 conf/lunaAnimations-${MACHINE}.conf ${D}${sysconfdir}/palm/lunaAnimations-platform.conf
	fi

	# install the platform defaultPreferences.txt file
#	bbnote "install the platform defaultPreferences.txt file"
	if [ -f conf/defaultPreferences-${MACHINE}.txt ]
	then
		install	-m 644 conf/defaultPreferences-${MACHINE}.txt ${D}${sysconfdir}/palm/defaultPreferences-platform.txt
	fi

    if [ -d platform/${MACHINE} ]
    then
        # copy over platform specific images
        if [ -d platform/${MACHINE}/images ]
        then
	        cd ${S}/platform/${MACHINE} && tar --exclude=.svn -cf - images | tar xf - -C ${D}/usr/palm/sysmgr
            cd ${S}
        fi
    fi

	# install the mojodb file to register schema for different security policies
	install -d ${D}${sysconfdir}/palm/db_kinds
	if [ -f mojodb/com.palm.securitypolicy ]
	then
		install	-m 644 mojodb/com.palm.securitypolicy ${D}${sysconfdir}/palm/db_kinds/com.palm.securitypolicy
	fi

	# install the mojodb file for the device security policy
	if [ -f mojodb/com.palm.securitypolicy.device ]
	then
		install	-m 644 mojodb/com.palm.securitypolicy.device ${D}${sysconfdir}/palm/db_kinds/com.palm.securitypolicy.device
	fi

	# install the mojodb file to set permissions on security policies
	install -d ${D}${sysconfdir}/palm/db/permissions
	if [ -f mojodb/com.palm.securitypolicy.permissions ]
	then
		install	-m 644 mojodb/com.palm.securitypolicy.permissions ${D}${sysconfdir}/palm/db/permissions/com.palm.securitypolicy
	fi

	# install the mojodb file to register for backup
	install -d ${D}${sysconfdir}/palm/backup
	if [ -f mojodb/com.palm.appDataBackup ]
	then
		install	-m 644 mojodb/com.palm.appDataBackup ${D}${sysconfdir}/palm/backup/com.palm.appDataBackup
	fi

	if [ -f conf/default-exhibition-apps.json ]
	then
		install -m 644 conf/default-exhibition-apps.json ${D}${sysconfdir}/palm
	fi

	# install the pubsub definition file for revokations
	if [ -f service/com.palm.appinstaller.pubsub ]
	then
		install -d ${D}${sysconfdir}/palm/pubsub_handlers
		install -m 0644 service/com.palm.appinstaller.pubsub ${D}${sysconfdir}/palm/pubsub_handlers/com.palm.appinstaller 
	fi
}

do_clean_prepend() {
	os.system('cd ' + bb.data.expand('${S}', d) + ' && [ -f Makefile ] && make distclean')
}

FILES_${PN} += "/usr/palm/  /etc/palm/  /usr/share/"
