DESCRIPTION = "Ultralightweight JSON parser in ANSI C"
HOMEPAGE = "https://github.com/DaveGamble/cJSON"
AUTHOR = "Dave Gamble"
SECTION = "libs"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=218947f77e8cb8e2fa02918dc41c50d0"

PV = "1.7.19"
SRCREV = "c859b25da02955fef659d658b8f324b5cde87be3"

SRC_URI = "git://github.com/DaveGamble/cJSON.git;protocol=https;tag=v${PV}"

inherit cmake

S = "${WORKDIR}/git"

EXTRA_OECMAKE += "\
    -DENABLE_CJSON_UTILS=On \
    -DENABLE_CJSON_TEST=Off \
    -DENABLE_CUSTOM_COMPILER_FLAGS=OFF \
    -DBUILD_SHARED_AND_STATIC_LIBS=On \
    -DCMAKE_INSTALL_PREFIX=/usr \
    -DCJSON_BUILD_DUMMY=OFF \
"

FILES_${PN} += "${libdir}/* \
                ${includedir}/cjson/*"
