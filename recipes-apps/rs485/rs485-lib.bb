SUMMARY = "RS485 library"
DESCRIPTION = "Package constain the dynamic library for reading data of RS485 sensor" 
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://github.com/tien-ce/Graduation-Project;branch=devtool;protocol=https"

SRCREV = "${AUTOREV}"
DEPENDS += "libmodbus"
RDEPENDS:${PN} += "libmodbus"
S = "${WORKDIR}/git/Components/RS485"


inherit cmake
