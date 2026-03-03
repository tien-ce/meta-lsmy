SUMMARY = "data correct library"
DESCRIPTION = "" 
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
SRC_URI = "git://github.com/tien-ce/Graduation-Project;branch=main;protocol=https"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git/Components/data_correctness"

inherit cmake
