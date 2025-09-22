require ${BPN}.inc

SUMMARY = "SWIG - Simplified Wrapper and Interface Generator"
HOMEPAGE = "http://swig.sourceforge.net/"
SECTION = "devel"
LICENSE = "BSD & GPLv3"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e7807a6282784a7dde4c846626b08fc6 \
                    file://LICENSE-GPL;md5=d32239bcb673463ab874e80d47fae504 \
                    file://LICENSE-UNIVERSITIES;md5=8ce9dcc8f7c994de4a408b205c72ba08"

DEPENDS = "libpcre python3"

SRC_URI = "${SOURCEFORGE_MIRROR}/${BPN}/${BPN}-${PV}.tar.gz \
           file://0001-Use-proc-self-exe-for-swig-swiglib-on-non-Win32-plat.patch \
           file://0001-configure-use-pkg-config-for-pcre-detection.patch"
SRC_URI[md5] = "c96a1d5ecb13d38604d7e92148c73c97"
SRC_URI[sha256] = "58a475dbbd4a4d7075e5fe86d4e54c9edde39847cdb96a3053d87cb64a23a453"

inherit autotools python3native

EXTRA_OECONF = " \
    --with-python=${PYTHON} \
    --without-allegrocl \
    --without-android \
    --without-boost \
    --without-chicken \
    --without-clisp \
    --without-csharp \
    --without-d \
    --without-gcj \
    --without-go \
    --without-guile \
    --without-java \
    --without-lua \
    --without-mzscheme \
    --without-ocaml \
    --without-pcre \
    --disable-ccache \
    --without-octave \
    --without-perl5 \
    --without-pike \
    --without-php \
    --without-r \
    --without-ruby \
    --without-tcl \
"

BBCLASSEXTEND = "native nativesdk"

do_configure() {
    install -m 0755 ${STAGING_DATADIR_NATIVE}/gnu-config/config.guess ${S}/Tools/config
    install -m 0755 ${STAGING_DATADIR_NATIVE}/gnu-config/config.sub   ${S}/Tools/config
    install -m 0755 ${STAGING_DATADIR_NATIVE}/gnu-config/config.guess ${S}
    install -m 0755 ${STAGING_DATADIR_NATIVE}/gnu-config/config.sub   ${S}
    oe_runconf
}

do_install_append_class-nativesdk() {
    cd ${D}${bindir}
    ln -s swig swig2.0
}

def swiglib_relpath(d):
    swiglib = d.getVar('datadir', True) + "/" + d.getVar('BPN', True) + "/" + d.getVar('PV', True)
    return os.path.relpath(swiglib, d.getVar('bindir', True))

do_install_append_class-native() {
    create_wrapper ${D}${bindir}/swig SWIG_LIB='`dirname $$(`realpath ${D}${bindir}/swig`)`/${@swiglib_relpath(d)}'
}
