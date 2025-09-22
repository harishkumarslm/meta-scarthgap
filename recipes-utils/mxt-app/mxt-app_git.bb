DESCRIPTION = "Command line utility for maXTouch devices"
SECTION = "tools"
HOMEPAGE = "https://github.com/atmel-maxtouch/mxt-app"
LICENSE = "ATMEL_LLA"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8b6acde4490765c7b838377ac61e2d2d"

DEPENDS = "libusb"

PV = "1.27+git${SRCPV}"

inherit autotools

SRC_URI = "git://github.com/atmel-maxtouch/mxt-app.git;protocol=https"
SRCREV = "c732ea25c9bd5271ae7d8219773e26d507d42a77"

S = "${WORKDIR}/git"
