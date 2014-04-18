# Imported from oe-core e97d152ca13556b41a236c1a4cfb11e77ff857d7. Can be dropped
# once we move to Yocto 1.6 .

TUNEVALID[neon] = "Enable Neon SIMD accelerator unit."
TUNE_CCARGS .= "${@bb.utils.contains("TUNE_FEATURES", "neon", bb.utils.contains("TUNE_FEATURES", "vfpv4", " -mfpu=neon-vfpv4", " -mfpu=neon", d), "" , d)}"
ARMPKGSFX_FPU .= "${@bb.utils.contains("TUNE_FEATURES", "neon", "-neon", "", d)}"
