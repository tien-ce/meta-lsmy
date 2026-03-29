SUMMARY = "Generate whitelist"
DESCRIPTION = "Generate whitelist for LSMY security agent"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://gen_whitelist.sh"

inherit systemd

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/gen_whitelist.sh ${D}${bindir}/gen_whitelist.sh
}

FILES:${PN} += " \
    ${bindir}/gen_whitelist.sh \
"