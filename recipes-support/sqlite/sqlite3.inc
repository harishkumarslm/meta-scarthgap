SUMMARY = "Embeddable SQL database engine"
HOMEPAGE = "https://www.sqlite.org"
DESCRIPTION = "The SQLite library is a software library that implements a self-contained, serverless, zero-configuration, transactional SQL database engine."

SECTION = "libs"
LICENSE = "PD"

LIC_FILES_CHKSUM = "file://sqlite3.h;endline=11;md5=786d3dc581eff03f4fd9e4a77ed00c66"

PE = "3"

def sqlite_download_version(d):
    pvsplit = d.getVar('PV').split('.')
    if len(pvsplit) < 4:
        pvsplit.append('0')
    return pvsplit[0] + ''.join([part.rjust(2, '0') for part in pvsplit[1:]])

SQLITE_PV = "${@sqlite_download_version(d)}"

S = "${WORKDIR}/sqlite-autoconf-${SQLITE_PV}"

UPSTREAM_CHECK_URI = "https://www.sqlite.org/"
UPSTREAM_CHECK_REGEX = "releaselog/(?P<pver>(\\d+[\\.\\-_]*)+)\\.html"

CVE_PRODUCT = "sqlite"

inherit autotools pkgconfig
