DESCRIPTION = "Microchip libplanes library for sama5 lcd controller"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

PACKAGES = "${PN}-dbg ${PN} ${PN}-python"

DEPENDS = "libdrm cairo cjson lua swig-native python3"

RDEPENDS:${PN} = "python3-core udev-rules-at91"

SRC_URI = "git://github.com/linux4sam/libplanes.git;protocol=https"
PV = "0.0.3+git${SRCPV}"

SRCREV = "5ea58244779658c1793b14f84d50542486505564"

S = "${WORKDIR}/git"

inherit pkgconfig autotools

EXTRA_OECONF += "--enable-shared --disable-static"

do_configure:prepend() {
    ( cd ${S};
    ${S}/autogen.sh; cd -)
}

INSANE_SKIP:${PN} = "dev-so"

FILES:${PN} += " \
    /opt/planes/planes-loop.sh \
    /opt/planes/planes-loop.py \
    /opt/ApplicationL* \
    ${libdir}/* \
    ${includedir}/* \
    ${bindir}/* \
    ${datadir}/planes/* \
"
FILES:${PN}-python = "${libdir}/python3.11/site-packages/*"

do_install:append() {
    install -Dm 0644 ${S}/scripts/planes.png  ${D}/opt/ApplicationLauncher/applications/resources/planes.png
    install -Dm 0644 ${S}/scripts/09-planes.xml  ${D}/opt/ApplicationLauncher/applications/xml/09-planes.xml
    install -Dm 0755 ${S}/scripts/planes-loop.sh ${D}/opt/planes/planes-loop.sh
    install -Dm 0755 ${S}/scripts/planes-loop.py ${D}/opt/planes/planes-loop.py
    install -Dm 0755 ${S}/python/examples/splash.py ${D}${datadir}/planes/splash.py
    rm -f ${D}${libdir}/python3.11/site-packages/planes/_planes.a
    rm -f ${D}${libdir}/libplanes.a
}
