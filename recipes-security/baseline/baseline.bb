SUMMARY = "Generate file integrity baseline"
DESCRIPTION = "Generate file integrity baseline for LSMY security agent"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://gen_baseline.sh"

inherit systemd

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/gen_baseline.sh ${D}${bindir}/gen_baseline.sh
}

FILES:${PN} += " \
    ${bindir}/gen_baseline.sh \
"