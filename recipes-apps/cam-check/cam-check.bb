SUMMARY = "Camera check and diagnostic application"
DESCRIPTION = "Simple camera check utility used to verify camera availability and basic functionality on the target system"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://github.com/nguyenthinhthanh/IoT-AI-Laboratory-Safety-System-on-CoreIoT-with-Yocto-Linux.git;branch=main;protocol=https"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git/src/cam-check"

DEPENDS += "opencv"

RDEPENDS:${PN} += " \
    opencv \
    libx11 \
    xauth \
"

inherit cmake