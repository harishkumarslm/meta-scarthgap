SUMMARY = "Atmel SAM-BA In-system Programmer"
SECTION = "devel"
LICENSE = "ATMEL_LLA_SAM-BA"
LIC_FILES_CHKSUM = "file://doc/license.txt;md5=1d90164463a7334a802991ec50f5d2f2"

SRC_URI = "http://www.atmel.com/Images/sam-ba_${PV}_linux.zip"
SRC_URI[md5] = "a9cf0fc282fda1b02ba7f6da3cc347f4"
SRC_URI[sha256] = "302734f53e471b5d1a292c9af937262f4fd766ec47ebfea62439761e7c1cd9d7"

S = "${WORKDIR}/sam-ba_cdc_linux"

do_install() {
    install -d ${D}${bindir}/sam-ba_cdc_linux
    cp -a ${S}/. ${D}${bindir}/sam-ba_cdc_linux/
}

INSANE_SKIP:${PN} += "already-stripped"

BBCLASSEXTEND = "native nativesdk"
