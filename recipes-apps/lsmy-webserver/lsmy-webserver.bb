SUMMARY = "LSMY embedded web server service"
DESCRIPTION = "Embedded web server component for LSMY system, providing local endpoints for configuration and monitoring"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://github.com/nguyenthinhthanh/IoT-AI-Laboratory-Safety-System-on-CoreIoT-with-Yocto-Linux.git;branch=main;protocol=https"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git/src/lsmy-webserver"

inherit python3-dir

RDEPENDS:${PN} += " \
    python3-core \
    lsmy-webserver-service \
    lsmy-python-lib \
"

do_install() {
    install -d ${D}${PYTHON_SITEPACKAGES_DIR}/lsmy_webserver
    cp -r ${S}/lsmy_webserver/* \
        ${D}${PYTHON_SITEPACKAGES_DIR}/lsmy_webserver/

    install -d ${D}/usr/share/lsmy_webserver/webserver
    cp -r ${S}/webserver/* ${D}/usr/share/lsmy_webserver/webserver/
}

FILES:${PN} += " \
    ${PYTHON_SITEPACKAGES_DIR}/lsmy_webserver \
    /usr/share/lsmy_webserver \
"
