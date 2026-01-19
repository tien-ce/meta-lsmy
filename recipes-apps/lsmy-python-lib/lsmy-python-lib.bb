SUMMARY = "LSMY Python support library"
DESCRIPTION = "Python modules providing high-level logic for the LSMY system"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://github.com/nguyenthinhthanh/IoT-AI-Laboratory-Safety-System-on-CoreIoT-with-Yocto-Linux.git;branch=main;protocol=https"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git/src/lsmy-python-lib"

RDEPENDS:${PN} += "python3-core"

do_install() {
    install -d ${D}${PYTHON_SITEPACKAGES_DIR}/lsmy_python_lib
    cp -r ${S}/lsmy_python_lib/* \
        ${D}${PYTHON_SITEPACKAGES_DIR}/lsmy_python_lib/
}

FILES:${PN} += "${PYTHON_SITEPACKAGES_DIR}/lsmy_python_lib"