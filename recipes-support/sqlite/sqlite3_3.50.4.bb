require sqlite3.inc

LICENSE = "PD"
LIC_FILES_CHKSUM = "file://sqlite3.h;endline=11;md5=786d3dc581eff03f4fd9e4a77ed00c66"

SRC_URI = "https://www.sqlite.org/2025/sqlite-autoconf-3500400.tar.gz"
SRC_URI[md5sum] = "d1501f8c493c29e4abb1f5b1d81907de"        # Fill in actual md5 after download!
SRC_URI[sha256sum] = "e03ef12b98b76a4e44e7e25cfbf53f7a8747c7f786c73f8bc3c2b9ac1617685e" # Fill in actual sha256 after download!

SUMMARY = "Embeddable SQL database engine"
HOMEPAGE = "https://www.sqlite.org"
SECTION = "libs"

PE = "3"

def sqlite_download_version(d):
    pvsplit = d.getVar('PV').split('.')
    if len(pvsplit) < 4:
        pvsplit.append('0')
    return pvsplit[0] + ''.join([part.rjust(2,'0') for part in pvsplit[1:]])

SQLITE_PV = "${@sqlite_download_version(d)}"
S = "${WORKDIR}/sqlite-autoconf-${SQLITE_PV}"

UPSTREAM_CHECK_URI = "https://www.sqlite.org/"
UPSTREAM_CHECK_REGEX = "releaselog/(?P<pver>(\d+[\.\-_]*)+)\.html"

inherit autotools pkgconfig

PACKAGECONFIG ?= ""
PACKAGECONFIG_class-native = ""
PACKAGECONFIG[editline] = "--enable-editline,--disable-editline,libedit"
PACKAGECONFIG[readline] = "--enable-readline,--disable-readline,readline ncurses"

EXTRA_OECONF = " --enable-shared --enable-threadsafe --disable-static-shell "

CFLAGS_append = " -fPIC"
BUILD_CFLAGS += "-DUSE_PREAD"
BUILDSDK_CFLAGS += "-DUSE_PREAD"
TARGET_CFLAGS += "-DUSE_PREAD"
BUILD_CFLAGS += "-DSQLITE_ENABLE_COLUMN_METADATA"
BUILDSDK_CFLAGS += "-DSQLITE_ENABLE_COLUMN_METADATA"
TARGET_CFLAGS += "-DSQLITE_ENABLE_COLUMN_METADATA"

PACKAGES = "lib${BPN} lib${BPN}-dev lib${BPN}-doc ${PN}-dbg lib${BPN}-staticdev ${PN}"

FILES:${PN} = "${bindir}/*"
FILES:lib${BPN} = "${libdir}/*.so.*"
FILES:lib${BPN}-dev = "${libdir}/*.la ${libdir}/*.so ${libdir}/pkgconfig ${includedir}"
FILES:lib${BPN}-doc = "${docdir} ${mandir} ${infodir}"
FILES:lib${BPN}-staticdev = "${libdir}/lib*.a"
AUTO_LIBNAME_PKGS = "${MLPREFIX}lib${BPN}"

BBCLASSEXTEND = "native nativesdk"
