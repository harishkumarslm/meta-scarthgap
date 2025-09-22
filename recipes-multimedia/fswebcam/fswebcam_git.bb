DESCRIPTION = "Webcam image grabber and manipulation application."
SECTION = "graphics"
HOMEPAGE = "http://www.sanslogic.co.uk/fswebcam/"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=393a5ca445f6965873eca0259a17f833"

DEPENDS = "gd"

inherit autotools-brokensep

SRCREV = "c515c9adebe5422885a653eba21bf27622ee3fc8"
SRC_URI = "git://github.com/fsphil/fswebcam.git;protocol=https"

S = "${WORKDIR}/git"
