SUMMARY = "Deploy TensorFlow Lite AI models for the LSMY system"
DESCRIPTION = "Installs TensorFlow Lite (.tflite) AI model files for the LSMY system into /usr/share/models for runtime inference"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://github.com/nguyenthinhthanh/IoT-AI-Laboratory-Safety-System-on-CoreIoT-with-Yocto-Linux.git;branch=main;protocol=https"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git/ai/models/deploys"

do_install() {
    install -d ${D}/usr/share/models
    install -m 0644 ${S}/*.tflite ${D}/usr/share/models/
    install -m 0644 ${S}/*.task ${D}/usr/share/models/
}

FILES:${PN} += "/usr/share/models"