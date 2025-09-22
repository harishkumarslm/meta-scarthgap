SUMMARY = "Hantro plugins for GStreamer"
SECTION = "multimedia"
LICENSE = "LGPL-2.1-only"
HOMEPAGE = "http://www.gstreamer.net/"
DEPENDS = "gstreamer1.0 gstreamer1.0-plugins-base g1-decoder libdrm"
include gstreamer1.0-plugins-hantro.inc

inherit autotools-brokensep pkgconfig gettext

LIC_FILES_CHKSUM = "file://COPYING.LESSER;md5=4fbd65380cdd255951079008b364516c"

S = "${WORKDIR}/git"
SRC_URI = "gitsm://github.com/linux4sam/gst1-hantro-g1.git;branch=master;protocol=https"

SRCREV = "4850e2e6b02f67bd55560e64664908af63425cc7"

include gstreamer1.0-plugins-hantro.inc

FILES:${PN} += "${libdir}/gstreamer-1.0/*.so"
FILES:${PN}-dbg += "${libdir}/gstreamer-1.0/.debug"
FILES:${PN}-dev += "${libdir}/gstreamer-1.0/*.la"
FILES:${PN}-staticdev += "${libdir}/gstreamer-1.0/*.a"

COMPATIBLE_MACHINE = "sama5d4|rugged-board-a5d2x|rugged-board-a5d2x-sd1"
