# Standard Cortex-A5 tune file for Scarthgap (Yocto 5.0)
   # Based on poky/meta/conf/machine/include/arm/tune-cortexa5.inc

   DEFAULTTUNE = "cortexa5thf-neon"

   TUNEVALID[armv5] = "Enable Cortex-A5 specific optimizations"
   TUNEVALID[armv6] = "Enable Cortex-A5 specific optimizations"
   TUNEVALID[thumb] = "Enable Cortex-A5 specific optimizations"
   TUNEVALID[armhf] = "Enable Cortex-A5 specific optimizations"
   TUNEVALID[neon] = "Enable Cortex-A5 specific optimizations"
   TUNEVALID[vfp] = "Enable Cortex-A5 specific optimizations"

   ARMPKGARCH:tune-cortexa5 = "cortexa5"
   ARMPKGARCH:tune-cortexa5t = "cortexa5"
   ARMPKGARCH:tune-cortexa5hf = "cortexa5"
   ARMPKGARCH:tune-cortexa5thf = "cortexa5"
   ARMPKGARCH:tune-cortexa5-neon = "cortexa5"
   ARMPKGARCH:tune-cortexa5t-neon = "cortexa5"
   ARMPKGARCH:tune-cortexa5hf-neon = "cortexa5"
   ARMPKGARCH:tune-cortexa5thf-neon = "cortexa5"

   TUNE_CCARGS .= " ${@bb.utils.contains('TUNE_FEATURES', 'neon', ' -mfpu=neon', '', d)}"

   # Base features
   BASELIB_tune-cortexa5 = "lib"

   # FPU settings
   TUNE_FEATURES:append = " ${@bb.utils.contains('TUNE_FEATURES', 'callconvention-hard', ' callconvention-hard', '', d)}"
