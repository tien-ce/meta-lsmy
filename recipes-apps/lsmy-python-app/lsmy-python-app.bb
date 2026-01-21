SUMMARY = "LSMY Python application runtime"
DESCRIPTION = "Python application code that implements the main runtime logic and execution flow invoked by the run-lsmy entrypoint"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://github.com/nguyenthinhthanh/IoT-AI-Laboratory-Safety-System-on-CoreIoT-with-Yocto-Linux.git;branch=main;protocol=https"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git/src/lsmy-python-app"

inherit python3-dir

RDEPENDS:${PN} += " \
    python3-core \
    python3-ctypes \
    lsmy-hello \
    lsmy-python-lib \
"

do_install() {
    install -d ${D}${PYTHON_SITEPACKAGES_DIR}/lsmy_python_app
    cp -r ${S}/lsmy_python_app/* \
        ${D}${PYTHON_SITEPACKAGES_DIR}/lsmy_python_app/
}

FILES:${PN} += "${PYTHON_SITEPACKAGES_DIR}/lsmy_python_app"
