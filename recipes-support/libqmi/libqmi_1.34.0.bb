SUMMARY = "libqmi is a library for talking to WWAN devices by QMI protocol"
DESCRIPTION = "libqmi is a glib-based library for talking to WWAN modems and devices which speak the Qualcomm MSM Interface (QMI) protocol"
HOMEPAGE = "https://gitlab.freedesktop.org/mobile-broadband/libqmi"
LICENSE = "GPLv2 & LGPLv2.1"
LIC_FILES_CHKSUM = " \
    file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://COPYING.LIB;md5=4fbd65380cdd255951079008b364516c \
"

PV = "1.34.0"

SRC_URI = "https://gitlab.freedesktop.org/mobile-broadband/libqmi/-/archive/libqmi-${PV}/libqmi-${PV}.tar.bz2 \
           file://0001-Detect-clang-new.patch"

SRC_URI[md5sum] = "e6e5a35a6c2f3cd5e0a5b4d9c9b05c8d"
SRC_URI[sha256sum] = "60e6e7b33f662b1fd3747b3c6e7c7c4e1a9848dadaa1c789e3d3e3a9b4a5b2c1"

DEPENDS = "glib-2.0 glib-2.0-native libgudev libmbim"

inherit autotools pkgconfig bash-completion gobject-introspection

FILES_${PN} += "${libdir}/libqmi-glib.so.* \
                ${bindir}/qmi* \
                ${datadir}/bash-completion/completions/qmi*"

INSANE_SKIP_${PN} += "dev-so"
