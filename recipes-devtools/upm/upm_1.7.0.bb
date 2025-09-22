SUMMARY = "Sensor/Actuator repository for Mraa"
HOMEPAGE = "https://github.com/intel-iot-devkit/upm"
SECTION = "libs"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=66493d54e65bfc12c7983ff2e884f37f"

DEPENDS = "libjpeg-turbo mraa swig-native python3-native nodejs-native"
SRC_URI = "git://github.com/intel-iot-devkit/upm.git;protocol=https;branch=master \
           file://0004-remove-examples-from-cmakefile.patch"

SRCREV = "67b77b78aa21dbc249acbc6693972f638bbfc962"
S = "${WORKDIR}/git"

COMPATIBLE_HOST = "(x86_64.*|i.86.*|aarch64.*|arm.*)-linux"

inherit distutils3-base cmake python3native pkgconfig

EXTRA_OECMAKE += "-DBUILDEXAMPLES=ON"

# Language bindings (Python3 default, nodejs optional)
BINDINGS ?= "python3"
PACKAGECONFIG ??= "${@bb.utils.contains('PACKAGES', 'node-${PN}', 'nodejs', '', d)} \
                   ${@bb.utils.contains('PACKAGES', '${PYTHON3_PN}-${PN}', 'python3', '', d)}"

PACKAGECONFIG[python3] = "-DBUILDSWIGPYTHON=ON, -DBUILDSWIGPYTHON=OFF, swig-native python3-native,"
PACKAGECONFIG[nodejs] = "-DBUILDSWIGNODE=ON, -DBUILDSWIGNODE=OFF, swig-native nodejs-native,"

FILES:${PYTHON3_PN}-${PN} = "${PYTHON_SITEPACKAGES_DIR}"
RDEPENDS:${PYTHON3_PN}-${PN} += "python3"

FILES:node-${PN} = "${prefix}/lib/node_modules/"
RDEPENDS:node-${PN} += "nodejs"

PACKAGES =+ "${@bb.utils.contains('BINDINGS', 'nodejs', 'node-${PN}', '', d)}"
PACKAGES =+ "${@bb.utils.contains('BINDINGS', 'python3', '${PYTHON3_PN}-${PN}', '', d)}"

INSANE_SKIP:${PN} = "installed-vs-shipped"
