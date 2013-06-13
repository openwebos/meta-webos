# Copyright (c) 2013 LG Electronics, Inc.
#
# webos_base
#
# This class adds two useful tasks analogous to fetchall in 
# openembedded-core/meta/classes/utility-tasks.bbclass.
# Patchall is used to prepare the sources for OpenGrok.

addtask unpackall after do_unpack
do_unpackall[recrdeptask] = "do_unpackall do_unpack"
do_unpackall() {
    :
}

addtask patchall after do_patch
do_patchall[recrdeptask] = "do_patchall do_patch"
do_patchall() {
    :
}
