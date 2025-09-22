SUMMARY = "Sample Python programs for UPM"
LICENSE = "CLOSED"
SRC_URI = "file://*"
S = "${WORKDIR}"

do_install() {
    install -d ${D}/home/root/upm-examples
    install -m 0644 ${S}/*.py ${D}/home/root/upm-examples/
}

FILES:${PN} = "/home/root/upm-examples/*"
