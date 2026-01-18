SUMMARY = "Hello World Python application"
DESCRIPTION = "Prints hello world every 3 seconds"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://github.com/nguyenthinhthanh/IoT-AI-Laboratory-Safety-System-on-CoreIoT-with-Yocto-Linux.git;branch=main;protocol=https"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

RDEPENDS:${PN} += "python3-core"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 \
        ${S}/src/hello-app/hello-py/hello.py \
        ${D}${bindir}/hello-py
}

FILES:${PN} += "${bindir}/hello-py"
