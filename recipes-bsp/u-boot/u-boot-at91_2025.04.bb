require u-boot-atmel.inc

DESCRIPTION = "U-Boot for Microchip AT91 - linux4microchip-2025.04 (master)"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://Licenses/gpl-2.0.txt;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
                    file://README;md5=4f975ca8dec794409f5dd55986d985cf"

PV = "v2025.04+git${SRCPV}"

SRCREV = "c693f38f2bb3f2ee5d3592d116870a65da081a05"
SRCBRANCH = "master"
SRC_URI = "git://github.com/linux4sam/u-boot-at91.git;branch=${SRCBRANCH};protocol=https"

S = "${WORKDIR}/git"

COMPATIBLE_MACHINE = "(sama5d3xek|sama5d3-xplained|sama5d3-xplained-sd|at91sam9x5ek|at91sam9rlek|at91sam9m10g45ek|sama5d4ek|sama5d4-xplained|sama5d4-xplained-sd|sama5d2-xplained|sama5d2-xplained-sd|sama5d2-xplained-emmc|sama5d2-ptc-ek|sama5d2-ptc-ek-sd|sama5d27-som1-ek|sama5d27-som1-ek-sd)"

SRC_URI += "${@bb.utils.contains('COMPATIBLE_MACHINE', 'sama5d2.*', 'file://fw_env.config', '', d)}"

PACKAGE_ARCH = "${MACHINE_ARCH}"
