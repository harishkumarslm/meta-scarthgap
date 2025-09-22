SUMMARY = "Sensor/Actuator repository for Mraa"
HOMEPAGE = "https://github.com/intel-iot-devkit/upm"
SECTION = "libs"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=66493d54e65bfc12c7983ff2e884f37f"

DEPENDS = "libjpeg-turbo mraa-data swig-native python3-native nodejs-native"
SRC_URI = "git://github.com/intel-iot-devkit/upm.git;protocol=https;branch=master \
           file://0004-remove-examples-from-cmakefile.patch \
           file://0007-starting-with-a-files.patch \
           file://0008-starting-with-b-files.patch \
           file://0009-starting-with-cde-files.patch \
           file://0010-starting-with-fghi-files.patch \
           file://0011-starting-with-jklm-files.patch \
           file://0012-starting-with-noprs-files.patch \
           file://0015-starting-with-t-files.patch \
           file://0016-starting-with-uv-files.patch \
           file://0001-starting-with-w-files.patch \
           file://0001-starting-with-xy-files.patch \
           file://0001-added-support-for-examples-in-library.patch"

SRCREV = "67b77b78aa21dbc249acbc6693972f638bbfc962"
S = "${WORKDIR}/git"

COMPATIBLE_HOST = "(x86_64.*|i.86.*|aarch64.*|arm.*)-linux"

inherit distutils3-base cmake python3native pkgconfig

EXTRA_OECMAKE += "-DBUILDEXAMPLES=ON"

BINDINGS ?= "python3"
PACKAGECONFIG ??= "${@bb.utils.contains('PACKAGES', 'node-${PN}', 'nodejs', '', d)} \
                   ${@bb.utils.contains('PACKAGES', '${PYTHON3_PN}-${PN}', 'python3', '', d)}"

PACKAGECONFIG[python3] = "-DBUILDSWIGPYTHON=ON, -DBUILDSWIGPYTHON=OFF, swig-native python3-native,"
PACKAGECONFIG[nodejs] = "-DBUILDSWIGNODE=ON, -DBUILDSWIGNODE=OFF, swig-native nodejs-native,"

FILES:${PYTHON3_PN}-${PN} = "${PYTHON_SITEPACKAGES_DIR}"
RDEPENDS:${PYTHON3_PN}-${PN} += "python3"

FILES:node-${PN} = "${prefix}/lib/node_modules/"
RDEPENDS:node-${PN} += "nodejs"

do_install_append() {
    install -d ${D}/data/lib
    install -d ${D}/usr/lib
    cd ${D}/usr/lib/
    cp -a *.so.1.7.0 ${D}/data/lib/
    rm -f *.so.1
    rm -f *.so.1.7.0
    ln -sf /data/lib/libupm-bmp280.so.1.7.0 libupm-bmp280.so.1
    ln -sf /data/lib/libupmc-bmp280.so.1.7.0 libupmc-bmp280.so.1
    ln -sf /data/lib/libupmc-lsm6dsl.so.1.7.0 libupmc-lsm6dsl.so.1
    ln -sf /data/lib/libupmc-utilities.so.1.7.0 libupmc-utilities.so.1
    ln -sf /data/lib/libupm-interfaces.so.1.7.0 libupm-interfaces.so.1
    ln -sf /data/lib/libupm-lsm6dsl.so.1.7.0 libupm-lsm6dsl.so.1
    ln -sf /data/lib/libupm-mcp9808.so.1.7.0 libupm-mcp9808.so.1
    ln -sf /data/lib/libupm-utilities.so.1.7.0 libupm-utilities.so.1
    ln -sf /data/lib/libupm-zfm20.so.1.7.0 libupm-zfm20.so.1
}

INSANE_SKIP:${PN} = "installed-vs-shipped ldflags"

FILES:${PN} = "/data/lib/*.so.1.7.0 /usr/lib/*.so.1"
