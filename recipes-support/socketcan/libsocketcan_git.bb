# File: libsocketcan_git.bb

SUMMARY = "Control basic functions in SocketCAN from userspace"
HOMEPAGE = "https://github.com/linux-can/libsocketcan"
SECTION = "libs/network"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"
SRC_URI = "git://github.com/linux-can/libsocketcan.git;protocol=https;branch=master"
SRCREV = "206cb739f1b67e4b53d4e9d9d6e48be1af522b65"  # latest as of 2024-10-09[web:111]
PV = "0.0.12+git${SRCPV}"
S = "${WORKDIR}/git"
inherit autotools pkgconfig
