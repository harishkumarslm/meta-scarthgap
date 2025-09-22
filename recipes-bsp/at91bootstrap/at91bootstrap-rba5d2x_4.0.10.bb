require at91bootstrap.inc

SUMMARY = "AT91Bootstrap for Rugged Board A5D2X (4.0.10)"
LICENSE = "ATMEL"
LIC_FILES_CHKSUM = "file://main.c;endline=27;md5=ba8b9b11ebaaedce38ea5a33ca9bf61c"

SRC_URI = "git://github.com/linux4sam/at91bootstrap.git;branch=master;protocol=https \
           file://Add_-fno-builtin_to_CPPFLAGS_for_gcc7.patch \
           file://Creating-symlink-to-binary.patch \
           file://Remove-standard-includes.patch"

PV = "4.0.10+git${SRCPV}"

SRCREV = "32bbb4f73a9f3e94326f709413c1b58baefdfbfa"

S = "${WORKDIR}/git"

COMPATIBLE_MACHINE = "(rugged-board-a5d2x|rugged-board-a5d2x-sd1|sama5d2-xplained|sama5d2-xplained-sd|sama5d2-xplained-emmc|sama5d2-ptc-ek|sama5d2-ptc-ek-sd|sama5d27-som1-ek|sama5d27-som1-ek-sd|sama5d4ek|sama5d4-xplained|sama5d4-xplained-sd|sama5d3xek|sama5d3-xplained|sama5d3-xplained-sd|at91sam9x5ek|at91sam9rlek|at91sam9m10g45ek)"
