DESCRIPTION = "Microchip WILC3000 Wi-Fi and Bluetooth firmware files"
SECTION = "firmware"
LICENSE = "CLOSED"
PR = "r1"

SRC_URI = "file://firmware/mchp/wilc3000_wifi_firmware.bin \
           file://firmware/mchp/wilc3000_ble_firmware.bin \
           file://firmware/mchp/wilc3000_ble_firmware_no_rtc.bin"

S = "${WORKDIR}/firmware"

do_install() {
    install -d ${D}${nonarch_base_libdir}/firmware/mchp
    install -m 0644 ${S}/mchp/wilc3000_wifi_firmware.bin ${D}${nonarch_base_libdir}/firmware/mchp/
    install -m 0644 ${S}/mchp/wilc3000_ble_firmware.bin ${D}${nonarch_base_libdir}/firmware/mchp/
    install -m 0644 ${S}/mchp/wilc3000_ble_firmware_no_rtc.bin ${D}${nonarch_base_libdir}/firmware/mchp/
}

FILES_${PN} = "${nonarch_base_libdir}/firmware/mchp/*"

INSANE_SKIP_${PN} = "ldflags installed-vs-shipped"
