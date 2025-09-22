SUMMARY = "Linux Library for low speed I/O Communication"
HOMEPAGE = "https://github.com/intel-iot-devkit/mraa"
SECTION = "libs"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=91e7de50a8d3cf01057f318d72460acd"
SRC_URI = "git://github.com/intel-iot-devkit/mraa.git;branch=master;protocol=https \
           file://0001-added-support-for-AM5D27-Rugged-board.patch \
           file://0002-pwm_support_sama5d2.patch \
           file://0001-added-support-for-adc-in-mikrobus.patch \
           file://0003-added-support-for-uart3-and-gpios.patch \
           file://0001-FindNodejs.cmake-parse-V8_MAJOR_VERSION-from-nodejs-.patch"
SRCREV = "81ecdb68ee876b2df4c45b0a762f7c55c58d129e"
S = "${WORKDIR}/git"
COMPATIBLE_HOST = "(x86_64.*|i.86.*|aarch64.*|arm.*)-linux"

inherit cmake python3-dir pkgconfig

DEPENDS += "json-c swig-native python3-native nodejs-native"
EXTRA_OECMAKE += " -DINSTALLTOOLS:BOOL=ON -DFIRMATA=ON -DCMAKE_SKIP_RPATH=ON"

FILES:${PN}-doc += "${datadir}/mraa/examples/"
FILES:${PN}-utils = "${bindir}/*"

BINDINGS ?= "python3 nodejs"
PACKAGECONFIG ??= "${BINDINGS}"
PACKAGECONFIG[python3] = "-DBUILDSWIGPYTHON=ON, -DBUILDSWIGPYTHON=OFF, swig-native python3-native,"
PACKAGECONFIG[nodejs] = "-DBUILDSWIGNODE=ON, -DBUILDSWIGNODE=OFF, swig-native nodejs-native,"
PACKAGECONFIG[ft4222] = "-DUSBPLAT=ON -DFTDI4222=ON, -DUSBPLAT=OFF -DFTDI4222=OFF,, libft4222"

FILES:${PYTHON3_PN}-${PN} = "${PYTHON_SITEPACKAGES_DIR}/* ${datadir}/mraa/examples/python/"
RDEPENDS:${PYTHON3_PN}-${PN} += "python3-core"
FILES:node-${PN} = "${prefix}/lib/node_modules/* ${datadir}/mraa/examples/javascript/"
RDEPENDS:node-${PN} += "nodejs"

do_compile:prepend() {
    sed -i 's/-dirty//' ${S}/src/version.c
}

PACKAGES =+ "node-${PN} ${PYTHON3_PN}-${PN}"
