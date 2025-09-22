SUMMARY = "Initial bootstrap for AT91 ARM MPUs"
DESCRIPTION = " \
    at91bootstrap is the second-level bootloader for Atmel AT91  \
    SoCs. It provides a set of algorithms to manage the hardware \
    initialization and to download the main application (or a    \
    third-level bootloader) from specified boot media to         \
    main memory and start it.                                    \
"
AUTHOR = "Atmel Corporation"
HOMEPAGE = "http://www.at91.com/linux4sam/bin/view/Linux4SAM/AT91Bootstrap"
BUGTRACKER = "https://github.com/linux4sam/at91bootstrap/issues"
SECTION = "bootloaders"
LICENSE = "ATMEL"

inherit cml1 deploy

DEPENDS += "bc-native"

COMPATIBLE_MACHINE = "(rugged-board-a5d2x|rugged-board-a5d2x-sd1|sama5d2-xplained|sama5d2-xplained-sd|sama5d2-xplained-emmc|sama5d2-ptc-ek|sama5d2-ptc-ek-sd|sama5d27-som1-ek|sama5d27-som1-ek-sd|sama5d4ek|sama5d4-xplained|sama5d4-xplained-sd|sama5d3xek|sama5d3-xplained|sama5d3-xplained-sd|at91sam9x5ek|at91sam9rlek|at91sam9m10g45ek)"

AT91BOOTSTRAP_MACHINE ??= "${MACHINE}"

AT91BOOTSTRAP_CONFIG ??= "${AT91BOOTSTRAP_MACHINE}nf_uboot"
AT91BOOTSTRAP_CONFIG_at91sam9x5ek-sd = "${AT91BOOTSTRAP_MACHINE}sd_uboot"
AT91BOOTSTRAP_CONFIG_sama5d3-xplained-sd = "${AT91BOOTSTRAP_MACHINE}sd_uboot"
AT91BOOTSTRAP_CONFIG_sama5d4ek = "${AT91BOOTSTRAP_MACHINE}nf_uboot_secure"
AT91BOOTSTRAP_CONFIG_sama5d4-xplained = "${AT91BOOTSTRAP_MACHINE}nf_uboot_secure"
AT91BOOTSTRAP_CONFIG_sama5d4-xplained-sd = "${AT91BOOTSTRAP_MACHINE}sd_uboot_secure"
AT91BOOTSTRAP_CONFIG_sama5d2-xplained = "${AT91BOOTSTRAP_MACHINE}-bsrdf_uboot"
AT91BOOTSTRAP_CONFIG_sama5d2-xplained-emmc = "${AT91BOOTSTRAP_MACHINE}emmc_uboot"
AT91BOOTSTRAP_CONFIG_sama5d2-xplained-sd = "${AT91BOOTSTRAP_MACHINE}-bsrsd_uboot"
AT91BOOTSTRAP_CONFIG_sama5d2-ptc-ek-sd = "${AT91BOOTSTRAP_MACHINE}sd_uboot"
AT91BOOTSTRAP_CONFIG_sama5d27-som1-ek = "${AT91BOOTSTRAP_MACHINE}qspi_uboot"
AT91BOOTSTRAP_CONFIG_sama5d27-som1-ek-sd = "${AT91BOOTSTRAP_MACHINE}sd_uboot"
AT91BOOTSTRAP_CONFIG_rugged-board-a5d2x = "${AT91BOOTSTRAP_MACHINE}qspi_uboot"
AT91BOOTSTRAP_CONFIG_rugged-board-a5d2x-sd1 = "${AT91BOOTSTRAP_MACHINE}sd1_uboot"

AT91BOOTSTRAP_TARGET ??= "${AT91BOOTSTRAP_CONFIG}_defconfig"
AT91BOOTSTRAP_LOAD ??= "nandflashboot-uboot"
AT91BOOTSTRAP_LOAD_at91sam9x5ek-sd = "sdboot-uboot"
AT91BOOTSTRAP_LOAD_sama5d2-xplained = "dataflashboot-uboot"
AT91BOOTSTRAP_LOAD_sama5d2-xplained-sd = "sdboot-uboot"
AT91BOOTSTRAP_LOAD_sama5d27-ptc-ek-sd = "sdboot-uboot"
AT91BOOTSTRAP_LOAD_sama5d27-som1-ek = "qspiboot-uboot"
AT91BOOTSTRAP_LOAD_sama5d27-som1-ek-sd = "sdboot-uboot"
AT91BOOTSTRAP_LOAD_sama5d3-xplained-sd = "sdboot-uboot"
AT91BOOTSTRAP_LOAD_sama5d4-xplained-sd = "sdboot-uboot"
AT91BOOTSTRAP_LOAD_rugged-board-a5d2x = "qspiboot-uboot"
AT91BOOTSTRAP_LOAD_rugged-board-a5d2x-sd1 = "sd1boot-uboot"

AT91BOOTSTRAP_SUFFIX ?= "bin"
AT91BOOTSTRAP_IMAGE ?= "${AT91BOOTSTRAP_MACHINE}-${AT91BOOTSTRAP_LOAD}-${PV}.${AT91BOOTSTRAP_SUFFIX}"
AT91BOOTSTRAP_BINARY ?= "at91bootstrap.${AT91BOOTSTRAP_SUFFIX}"
AT91BOOTSTRAP_SYMLINK ?= "at91bootstrap-${AT91BOOTSTRAP_MACHINE}.${AT91BOOTSTRAP_SUFFIX}"

EXTRA_OEMAKE = 'CROSS_COMPILE=${TARGET_PREFIX} CC="${TARGET_PREFIX}gcc ${TOOLCHAIN_OPTIONS}"'

do_configure:prepend() {
    if [ -f "${S}/configs/${AT91BOOTSTRAP_TARGET}" ] && [ ! -f "${B}/.config" ]; then
        cp "${S}/configs/${AT91BOOTSTRAP_TARGET}" "${B}/.config"
    fi

    if [ -f "${WORKDIR}/defconfig" ] && [ ! -f "${B}/.config" ]; then
        cp "${WORKDIR}/defconfig" "${B}/.config"
    fi

    if [ ! -f "${B}/.config" ]; then
        bbfatal "No config files found for ${AT91BOOTSTRAP_TARGET}"
    fi
}

do_compile() {
    unset CFLAGS CPPFLAGS LDFLAGS
    oe_runmake
}

do_deploy() {
    install -Dm 0644 ${S}/binaries/${AT91BOOTSTRAP_BINARY} ${DEPLOYDIR}/${AT91BOOTSTRAP_IMAGE}

    cd ${DEPLOYDIR}
    rm -f ${AT91BOOTSTRAP_BINARY} ${AT91BOOTSTRAP_SYMLINK}
    ln -sf ${AT91BOOTSTRAP_IMAGE} ${AT91BOOTSTRAP_SYMLINK}
    ln -sf ${AT91BOOTSTRAP_IMAGE} ${AT91BOOTSTRAP_BINARY}

    rm -f boot.bin BOOT.BIN
    ln -sf ${AT91BOOTSTRAP_IMAGE} BOOT.BIN
}
addtask deploy before do_build after do_compile

PACKAGE_ARCH = "${MACHINE_ARCH}"
