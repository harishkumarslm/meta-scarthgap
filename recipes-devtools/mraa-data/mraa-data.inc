SUMMARY = "Low Level Skeleton Library for Communication on Intel platforms"
SECTION = "libs"
AUTHOR = "Brendan Le Foll, Tom Ingleby"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=91e7de50a8d3cf01057f318d72460acd"

inherit cmake python3-dir pkgconfig

DEPENDS += "json-c swig-native python3-native nodejs-native"

EXTRA_OECMAKE += " -DINSTALLTOOLS:BOOL=ON -DFIRMATA=ON -DCMAKE_SKIP_RPATH=ON"

FILES:${PN}-doc += "${datadir}/mraa/examples/"
PACKAGES =+ "${PN}-utils"
FILES:${PN}-utils = "${bindir}/*"

# By default build Python3 and Nodejs bindings, add java if you wish
BINDINGS ?= "python3 nodejs"

PACKAGECONFIG ??= "${BINDINGS}"
PACKAGECONFIG[python3] = "-DBUILDSWIGPYTHON=ON, -DBUILDSWIGPYTHON=OFF, swig-native python3-native,"
PACKAGECONFIG[nodejs] = "-DBUILDSWIGNODE=ON, -DBUILDSWIGNODE=OFF, swig-native nodejs-native,"
PACKAGECONFIG[java] = "-DBUILDSWIGJAVA=ON, -DBUILDSWIGJAVA=OFF, swig-native openjdk-17-native,"
PACKAGECONFIG[ft4222] = "-DUSBPLAT=ON -DFTDI4222=ON, -DUSBPLAT=OFF -DFTDI4222=OFF,, libft4222"

do_compile:prepend() {
    sed -i 's/-dirty//' ${S}/src/version.c
}

# Python 3 bindings packaging
FILES:${PYTHON3_PN}-${PN} = "${PYTHON_SITEPACKAGES_DIR}/* ${datadir}/mraa/examples/python/"
RDEPENDS:${PYTHON3_PN}-${PN} += "python3-core"

# Node.js bindings packaging
FILES:node-${PN} = "${prefix}/lib/node_modules/* ${datadir}/mraa/examples/javascript/"
RDEPENDS:node-${PN} += "nodejs"

# Optional Java bindings packaging
FILES:${PN}-java = "${libdir}/libmraajava.so \
    ${libdir}/java/ \
    ${datadir}/mraa/examples/java/ \
    ${libdir}/pkgconfig/mraajava.pc \
    ${libdir}/.debug/libmraajava.so"
RDEPENDS:${PN}-java += "openjdk-17"
INSANE_SKIP:${PN}-java = "debug-files"

export JAVA_HOME="${STAGING_DIR}/${BUILD_SYS}/usr/lib/jvm/openjdk-17-native"
cmake_do_generate_toolchain_file:append() {
    echo '
set (JAVA_AWT_INCLUDE_PATH ${JAVA_HOME}/include CACHE PATH "AWT include path" FORCE)
set (JAVA_AWT_LIBRARY ${JAVA_HOME}/lib/libjawt.so CACHE FILEPATH "AWT Library" FORCE)
set (JAVA_INCLUDE_PATH ${JAVA_HOME}/include CACHE PATH "java include path" FORCE)
set (JAVA_INCLUDE_PATH2 ${JAVA_HOME}/include/linux CACHE PATH "java include path" FORCE)
set (JAVA_JVM_LIBRARY ${JAVA_HOME}/lib/server/libjvm.so CACHE FILEPATH "path to JVM" FORCE)
' >> ${WORKDIR}/toolchain.cmake
}

PACKAGES =+ "${@bb.utils.contains('BINDINGS', 'java', '${PN}-java', '', d)}"
PACKAGES =+ "${@bb.utils.contains('BINDINGS', 'nodejs', 'node-${PN}', '', d)}"
PACKAGES =+ "${@bb.utils.contains('BINDINGS', 'python3', '${PYTHON3_PN}-${PN}', '', d)}"

INSANE_SKIP:${PN} = "installed-vs-shipped ldflags"
