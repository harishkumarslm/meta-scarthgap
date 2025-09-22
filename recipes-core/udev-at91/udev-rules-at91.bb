DESCRIPTION = "Extra udev rules for AT91 boards"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0-or-later;md5=fed54355545ffd980b814dab4a3b312c"

SRC_URI:append = " file://keyboard.rules"

S = "${WORKDIR}"

do_install () {
    install -d ${D}${nonarch_base_libdir}/udev/rules.d
    install -m 0644 ${WORKDIR}/keyboard.rules ${D}${nonarch_base_libdir}/udev/rules.d/
}

inherit allarch
