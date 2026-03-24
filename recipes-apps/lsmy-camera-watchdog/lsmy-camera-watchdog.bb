SUMMARY = "LSMY camera watchdog service"
DESCRIPTION = "Dedicated monitoring service for the LSMY camera subsystem."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://github.com/nguyenthinhthanh/IoT-AI-Laboratory-Safety-System-on-CoreIoT-with-Yocto-Linux.git;branch=main;protocol=https \
           file://camera-watchdog.service"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

inherit systemd

RDEPENDS:${PN} += " \
    python3-core \
"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 \
        ${S}/src/lsmy-camera-watchdog/lsmy-camera-watchdog.py \
        ${D}${bindir}/lsmy-camera-watchdog

    install -d ${D}${systemd_system_unitdir}
    install -m 0644 \
        ${WORKDIR}/camera-watchdog.service \
        ${D}${systemd_system_unitdir}/camera-watchdog.service
}

SYSTEMD_SERVICE:${PN} = "camera-watchdog.service"
SYSTEMD_AUTO_ENABLE:${PN} = "disable"

FILES:${PN} += " \
    ${bindir}/lsmy-camera-watchdog \
    ${systemd_system_unitdir}/camera-watchdog.service \
"