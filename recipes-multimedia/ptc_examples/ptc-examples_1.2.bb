SUMMARY = "PTC examples"
DESCRIPTION = "The PTC examples are composed of binary configurations and demo applications."
AUTHOR = "Microchip Technology Incorporated"
HOMEPAGE = "https://github.com/linux4sam/ptc_examples"
SECTION = "examples"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://src/COPYING;md5=e23fadd6ceef8c618fc1c65191d846fa"

DEPENDS = "libevdev libgpiod"
PV = "1.2+git${SRCPV}"

SRC_URI = "git://github.com/linux4sam/ptc_examples.git;protocol=https"
SRCREV = "f24e90c4272d21c1b75f258a5dad73b72e77eff6"

S = "${WORKDIR}/git"

EXTRA_OEMAKE = 'CROSS_COMPILE=${TARGET_PREFIX} \
                CFLAGS="${TARGET_CC_ARCH} ${TOOLCHAIN_OPTIONS} ${CFLAGS}" \
                LDFLAGS="${TARGET_CC_ARCH} ${TOOLCHAIN_OPTIONS} ${LDFLAGS}" \
                '

do_compile() {
    oe_runmake
}

do_install() {
    install -D -m 0755 --target-directory=${D}/usr/bin ${S}/src/ptc_qt*_demo
    install -D -m 0755 --target-directory=${D}/home/root ${S}/src/start_ptc_qt*
    install -D -m 0644 --target-directory=${D}${base_libdir}/firmware/microchip \
                       ${S}/fw/*.bin ${S}/cfg/*.bin
}

PACKAGE_ARCH = "${MACHINE}"
FILES:${PN} += "${base_libdir}/firmware/microchip \
                /home/root/start_ptc_qt* \
                "
