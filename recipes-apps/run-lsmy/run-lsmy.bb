SUMMARY = "LSMY system main application runner"
DESCRIPTION = "Main Python entrypoint that launches and supervises the LSMY IoT and AI monitoring system"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://github.com/nguyenthinhthanh/IoT-AI-Laboratory-Safety-System-on-CoreIoT-with-Yocto-Linux.git;branch=main;protocol=https \
           file://run-lsmy.service"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

inherit systemd

RDEPENDS:${PN} += " \
    python3-core \
    python3-ctypes \
    lsmy-app \
"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 \
        ${S}/src/run-lsmy/run-lsmy.py \
        ${D}${bindir}/run-lsmy

    install -d ${D}${systemd_system_unitdir}
    install -m 0644 \
        ${WORKDIR}/run-lsmy.service \
        ${D}${systemd_system_unitdir}/run-lsmy.service
}

SYSTEMD_SERVICE:${PN} = "run-lsmy.service"
# SYSTEMD_AUTO_ENABLE:${PN} = "enable"
SYSTEMD_AUTO_ENABLE:${PN} = "disable"

FILES:${PN} += " \
    ${bindir}/run-lsmy \
    ${systemd_system_unitdir}/run-lsmy.service \
"