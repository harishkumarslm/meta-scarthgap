FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append = " file://fragment.cfg;subdir=busybox-1.36.1"
