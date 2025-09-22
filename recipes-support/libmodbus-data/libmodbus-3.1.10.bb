require libmodbus-data.inc

PV = "3.1.10"

SRC_URI[md5sum] = "9e335a7c8a6f1356656905d3b6b83f3e"
SRC_URI[sha256sum] = "1f1c6f9588d3c7b2d23cb7cd6b3af445a0e667a208b6aecd2a09d9d7a2d0b7f8"

inherit autotools pkgconfig

DEPENDS = "autoconf-archive"

do_compile() {
    cd ${S}
    ./configure --host=${TARGET_SYS} --enable-static
    make
}

do_install() {
    install -d ${D}${libdir}
    install -m 0755 ${S}/src/.libs/libmodbus.so.5.1.0 ${D}${libdir}/
    ln -sf libmodbus.so.5.1.0 ${D}${libdir}/libmodbus.so.5
    ln -sf libmodbus.so.5.1.0 ${D}${libdir}/libmodbus.so
    install -d ${D}${includedir}/modbus
    install -m 0644 ${S}/src/modbus*.h ${D}${includedir}/modbus/
}

PACKAGES =+ "${PN}-dev ${PN}-dbg"

FILES_${PN} = "${libdir}/libmodbus.so.5*"
FILES_${PN}-dev = "${libdir}/libmodbus.so ${includedir}/modbus/*"
FILES_${PN}-dbg = "${libdir}/.debug/*"

INSANE_SKIP_${PN} += "dev-so ldflags"
