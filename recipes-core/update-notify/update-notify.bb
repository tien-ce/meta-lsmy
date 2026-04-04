SUMMARY = "LSMY update notify service"
DESCRIPTION = "A service to notify user when a new version is available"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://check-update.sh \
           file://check-update.service \
           file://check-update.timer \
           "

S = "${WORKDIR}"

inherit systemd

RDEPENDS:${PN} += " \
    opkg-config-server \
"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/check-update.sh ${D}${bindir}/check-update.sh

    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/check-update.service ${D}${systemd_system_unitdir}/check-update.service
    install -m 0644 ${WORKDIR}/check-update.timer ${D}${systemd_system_unitdir}/check-update.timer
}

SYSTEMD_SERVICE:${PN} = "check-update.timer"
# SYSTEMD_AUTO_ENABLE:${PN} = "enable"
SYSTEMD_AUTO_ENABLE:${PN} = "disable"

FILES:${PN} += " \
    ${bindir}/check-update.sh \
    ${systemd_system_unitdir}/check-update.service \
    ${systemd_system_unitdir}/check-update.timer \
"