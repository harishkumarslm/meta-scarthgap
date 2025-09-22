SUMMARY = "Low Level Skeleton Library for Communication on Intel platforms"
SECTION = "libs"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=91e7de50a8d3cf01057f318d72460acd"

inherit cmake python3-dir pkgconfig

DEPENDS += "json-c swig-native python3-native nodejs-native"
EXTRA_OECMAKE += " -DINSTALLTOOLS:BOOL=ON -DFIRMATA=ON -DCMAKE_SKIP_RPATH=ON"

FILES:${PN}-doc += "${datadir}/mraa/examples/"
FILES:${PN}-utils = "${bindir}/*"
