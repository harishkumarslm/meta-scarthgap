DESCRIPTION = "Init script for qtdemo"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://atmel-qt-demo-init-v1.1"

DEPENDS = "fbset"
RDEPENDS:${PN} = "udev-rules-at91"

do_install() {
    install -d ${D}${sysconfdir}/init.d/
    install -m 0755 ${WORKDIR}/atmel-qt-demo-init-v1.1 ${D}${sysconfdir}/init.d/qtdemo
}

inherit update-rc.d allarch

INITSCRIPT_NAME = "qtdemo"
INITSCRIPT_PARAMS = "start 99 5 2 . stop 19 0 1 6 ."
