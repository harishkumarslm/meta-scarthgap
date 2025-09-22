SUMMARY = "libmbim is a library for talking to WWAN devices by MBIM protocol"
DESCRIPTION = "libmbim is a glib-based library for talking to WWAN modems and devices which speak the Mobile Interface Broadband Model (MBIM) protocol"
HOMEPAGE = "https://gitlab.freedesktop.org/mobile-broadband/libmbim"
LICENSE = "GPLv2 & LGPLv2.1"
LIC_FILES_CHKSUM = " \
    file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://COPYING.LIB;md5=4fbd65380cdd255951079008b364516c \
"

PV = "1.30.0"

SRC_URI = "https://gitlab.freedesktop.org/mobile-broadband/libmbim/-/archive/libmbim-${PV}/libmbim-${PV}.tar.bz2 \
           file://clang-new.patch"

SRC_URI[md5sum] = "7f8df1a7740b7e8f68a0e7a3a9a4e0a3"
SRC_URI[sha256sum] = "c1e04f672c796c1a2aa1715f627ebb3a7f4a8b3a84b6a50f5c9e4973c2d7e14b"

DEPENDS = "glib-2.0 glib-2.0-native libgudev"

inherit autotools pkgconfig bash-completion gobject-introspection

FILES_${PN} += "${libdir}/libmbim-glib.so.* \
                ${bindir}/mbimcli \
                ${datadir}/bash-completion/completions/mbimcli"

INSANE_SKIP_${PN} += "dev-so"
