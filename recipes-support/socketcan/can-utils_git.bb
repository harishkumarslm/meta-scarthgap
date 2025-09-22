# File: can-utils_git.bb

SUMMARY = "Linux CAN network development utilities"
HOMEPAGE = "https://github.com/linux-can/can-utils"
SECTION = "console/network"
LICENSE = "GPLv2 & BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1795fa5a6cded96a089c3b09c6077baa"
DEPENDS = "libsocketcan"
SRC_URI = "git://github.com/linux-can/can-utils.git;protocol=https;branch=master"
SRCREV = "01083a64ebf28cc716efe2d2fd51c141042ae34b"  # commit for 2025.01 release[web:98][web:110]
S = "${WORKDIR}/git"
inherit autotools pkgconfig
RDEPENDS:${PN} += "iproute2"
