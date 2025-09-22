SUMMARY = "Paho MQTT - C libraries for the MQTT and MQTT-SN protocols"
DESCRIPTION = "Client implementation of open and standard messaging protocols for Machine-to-Machine (M2M) and Internet of Things (IoT)."
HOMEPAGE = "http://www.eclipse.org/paho/"
SECTION = "console/network"
LICENSE = "EPL-1.0 | EDL-1.0"
LIC_FILES_CHKSUM = " \
    file://edl-v10;md5=3adfcc70f5aeb7a44f3f9b495aa1fbf3 \
    file://epl-v10;md5=659c8e92a40b6df1d9e3dccf5ae45a08 \
    file://notice.html;md5=a00d6f9ab542be7babc2d8b80d5d2a4c \
    file://about.html;md5=dcde438d73cf42393da9d40fabc0c9bc \
"
SRC_URI = "https://github.com/eclipse-paho/paho.mqtt.c/releases/download/v${PV}/paho.mqtt.c-${PV}.tar.gz"
SRC_URI[md5sum] = "9d1ead73e678fa2f51a70a933b0bf017"
SRC_URI[sha256sum] = "0019dfc4b32d63c1392aa264aed2253c1e0c2fb09216f8e2cc269bbfb8bb49b5"

DEPENDS = "openssl"
S = "${WORKDIR}/paho.mqtt.c-${PV}"

inherit cmake pkgconfig

EXTRA_OECMAKE += "-DPAHO_BUILD_STATIC=OFF -DPAHO_BUILD_SHARED=ON -DPAHO_WITH_SSL=ON"

do_install:append() {
    install -d ${D}${includedir}
    install -m 0644 ${S}/src/MQTTAsync.h ${D}${includedir}
    install -m 0644 ${S}/src/MQTTClient.h ${D}${includedir}
    install -m 0644 ${S}/src/MQTTClientPersistence.h ${D}${includedir}
}

FILES:${PN} += "${libdir} ${includedir}"
