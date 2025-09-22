SUMMARY = "U-Boot for Microchip AT91 SoCs"
HOMEPAGE = "http://www.denx.de/wiki/U-Boot/WebHome"

inherit uboot-config

DEPENDS += "bc-native dtc-native"

# Enable Kconfig for modern U-Boot
CONFIG_USE_KCONFIG = "y"

# Default U-Boot configuration
UBOOT_MACHINE ?= "${MACHINE}_defconfig"

# SPL support
SPL_BINARY ?= "spl/u-boot-spl.bin"

# Scarthgap toolchain compatibility
EXTRA_OEMAKE += 'HOSTCC="${CC} ${CPPFLAGS}"'

# Additional configs for 2025.04 (e.g., CI support from commit)
EXTRA_OEMAKE += 'V=1'
