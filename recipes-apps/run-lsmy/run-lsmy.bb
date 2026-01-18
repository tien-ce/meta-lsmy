SUMMARY = "LSMY system main application runner"
DESCRIPTION = "Main Python entrypoint that launches and supervises the LSMY IoT and AI monitoring system"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://github.com/nguyenthinhthanh/IoT-AI-Laboratory-Safety-System-on-CoreIoT-with-Yocto-Linux.git;branch=main;protocol=https"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

RDEPENDS:${PN} += " \
    python3-core \
    lsmy-hello \
"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 \
        ${S}/src/run-lsmy/run-lsmy.py \
        ${D}${bindir}/run-lsmy
}

FILES:${PN} += "${bindir}/run-lsmy"