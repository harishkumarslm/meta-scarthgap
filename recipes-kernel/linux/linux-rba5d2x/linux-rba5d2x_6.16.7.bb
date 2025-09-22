SECTION = "kernel"
SUMMARY = "Linux kernel for Atmel ARM SoCs (aka AT91)"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

inherit kernel

S = "${WORKDIR}/git"
SRCREV = "v6.16.7"  # Use the actual commit hash for 6.16.7 from kernel.org stable tree
KBRANCH = "linux-6.16.y"  # Stable branch for 6.16.y series

SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux-stable.git;protocol=git;branch=${KBRANCH}"

SRC_URI += "file://defconfig"
SRC_URI[md5] = "c420b5fdadece5f49d37e91646dc7617"  # Adjust if defconfig changes

python __anonymous() {
    if d.getVar('UBOOT_FIT_IMAGE', True) == 'xyes':
        d.appendVar('DEPENDS', ' u-boot-mkimage-native dtc-native')
}

do_deploy_append() {
    if d.getVar('UBOOT_FIT_IMAGE', True) == 'xyes':
        dtb_path = "${B}/arch/${ARCH}/boot/dts/"
        # Fallback path if default does not exist
        if not os.path.exists(dtb_path):
            dtb_path = "${B}/arch/${ARCH}/boot/"
        its_file = f"{S}/arch/${ARCH}/boot/dts/${MACHINE}.its"
        if os.path.exists(its_file):
            bb.build.exec_func('cp', dtb_path, its_file)  # Use bitbake copy or shell cp
            bb.build.exec_func('mkimage', f"-f {MACHINE}.its {MACHINE}.itb", cwd=dtb_path)
            bb.build.exec_func('install', f"-m 0644 {MACHINE}.itb {DEPLOYDIR}/{MACHINE}.itb")
            # cd - automatically return to previous dir
}

kernel_do_configure_append() {
    bb.build.exec_func('rm', f"-f {B}/.scmversion {S}/.scmversion")
    bb.build.exec_func('git', 'status', cwd=S)
}

do_shared_workdir() {
    bb.build.exec_func('rm', f"-rf {S}/../image/boot/zImage {S}/../deploy-linux-rba5d2x/zImage")
}
addtask(shared_workdir, after='do_deploy_append')

KERNEL_MODULE_AUTOLOAD += "atmel_usba_udc g_serial"
KERNEL_MODULE_PACKAGE_SUFFIX="rugged_board"

COMPATIBLE_MACHINE = "(sama5d2-xplained|sama5d2-xplained-sd|sama5d2-ptc-ek|sama5d2-ptc-ek-sd|sama5d27-som1-ek|rugged-board-a5d2x|rugged-board-a5d2x-sd1|sama5d27-som1-ek-sd|sama5d4-xplained|sama5d4-xplained-sd|sama5d4ek|sama5d3-xplained|sama5d3-xplained-sd|sama5d3xek|at91sam9x5ek|at91sam9m10g45ek|at91sam9rlek)"

