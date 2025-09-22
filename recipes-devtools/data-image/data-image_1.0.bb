DESCRIPTION = "Data image for rugged-board-a5d2x (JFFS2 filesystem)"
LICENSE = "CLOSED"

SRC_URI = "file://data_lib.sh \
           file://interfaces \
           file://env.sh"

S = "${WORKDIR}"

# Dependencies for libraries and mkfs.jffs2
DEPENDS += "libmodbus-data lighttpd-data mraa-data upm-data mtd-utils-native"

do_install() {
    install -d ${D}/data
    install -d ${D}/etc/init.d
    install -d ${D}/usr/lib
    install -d ${DEPLOY_DIR_IMAGE}

    # Create temporary directories for image construction
    install -d ${WORKDIR}/data-image/lib
    install -d ${WORKDIR}/data-image/www/var
    install -d ${WORKDIR}/data-image/network

    # Copy scripts and configs
    install -m 0755 ${S}/data_lib.sh ${WORKDIR}/data-image/
    install -m 0644 ${S}/interfaces ${WORKDIR}/data-image/network/
    install -m 0755 ${S}/env.sh ${WORKDIR}/data-image/

    # Copy libraries (use WORKDIR paths from dependency builds)
    install -m 0644 ${WORKDIR}/../../mraa-data/*/image/data/lib/libmraa.so.2.0.0 ${WORKDIR}/data-image/lib/
    install -m 0644 ${WORKDIR}/../../libmodbus-data/*/libmodbus-data-3.1.4/src/.libs/libmodbus.so.5.1.0 ${WORKDIR}/data-image/lib/
    install -m 0644 ${WORKDIR}/../../lighttpd-data/*/build/src/.libs/mod_access.so ${WORKDIR}/data-image/lib/
    install -m 0644 ${WORKDIR}/../../lighttpd-data/*/build/src/.libs/mod_accesslog.so ${WORKDIR}/data-image/lib/
    install -m 0644 ${WORKDIR}/../../lighttpd-data/*/build/src/.libs/mod_dirlisting.so ${WORKDIR}/data-image/lib/
    install -m 0644 ${WORKDIR}/../../lighttpd-data/*/build/src/.libs/mod_indexfile.so ${WORKDIR}/data-image/lib/
    install -m 0644 ${WORKDIR}/../../lighttpd-data/*/build/src/.libs/mod_staticfile.so ${WORKDIR}/data-image/lib/

    # Generate JFFS2 image
    mkfs.jffs2 -lnp --root=${WORKDIR}/data-image --eraseblock=0x10000 -p -o ${DEPLOY_DIR_IMAGE}/data-image-rootfs.jffs2
}

# Update package files to include versioned libraries
FILES:${PN} += "/data/* /etc/init.d/* /usr/lib/libmraa.so* /usr/lib/libmodbus.so* /usr/lib/mod_*.so"

# Skip QA checks only where necessary
INSANE_SKIP:${PN} += "installed-vs-shipped"

# Machine-specific package
PACKAGE_ARCH = "${MACHINE_ARCH}"

# Restrict to rugged-board-a5d2x
COMPATIBLE_MACHINE = "(rugged-board-a5d2x|rugged-board-a5d2x-sd1)"
