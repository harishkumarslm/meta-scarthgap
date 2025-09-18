SUMMARY = "A small image just capable of allowing a device to boot."

IMAGE_FEATURES:append = " package-management"

IMAGE_INSTALL:append = " \
    packagegroup-core-boot \
    ${CORE_IMAGE_EXTRA_INSTALL} \
    sqlite3 \
    mraa \
    python3-mraa \
    upm \
    python3-upm \
    wpa-supplicant \
    paho-mqtt-c \
    firmware \
    e2fsprogs \
    kernel-modules \
    lighttpd \
    lighttpd-dbg \
    libmodbus \
    data-image \
"

IMAGE_LINGUAS = " "

LICENSE = "MIT"

inherit core-image

IMAGE_ROOTFS_SIZE ?= "8192"
IMAGE_ROOTFS_EXTRA_SPACE:append = " ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', ' + 4096', '', d)}"
