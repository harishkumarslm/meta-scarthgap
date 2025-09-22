require dtc.inc

LIC_FILES_CHKSUM = "file://GPL;md5=94d55d512a9ba36caa9b7df079bae19f \
                    file://libfdt/libfdt.h;beginline=3;endline=52;md5=fb360963151f8ec2d6c06b055bcbb68c"

SRCREV = "84e414b0b5bcea3a82875d79cc15520440e1e49b"
S = "${WORKDIR}/git"

BBCLASSEXTEND = "native nativesdk"

SUMMARY = "Device Tree Compiler"
HOMEPAGE = "https://devicetree.org/"
DESCRIPTION = "The Device Tree Compiler is a tool used to manipulate the Open-Firmware-like device tree used by PowerPC kernels."
SECTION = "bootloader"
LICENSE = "GPLv2 | BSD"

DEPENDS = "flex-native bison-native"

SRC_URI = "git://git.kernel.org/pub/scm/utils/dtc/dtc.git;protocol=git \
           file://make_install.patch"

UPSTREAM_CHECK_GITTAGREGEX = "v(?P<pver>\\d+(\\.\\d+)+)"

EXTRA_OEMAKE = 'NO_PYTHON=1 PREFIX="${prefix}" LIBDIR="${libdir}" DESTDIR="${D}"'

do_install() {
    oe_runmake install
}

PACKAGES += "${PN}-misc"

FILES:${PN}-misc = "${bindir}/convert-dtsv0 ${bindir}/ftdump ${bindir}/dtdiff"

RDEPENDS:${PN}-misc = "bash diffutils"

