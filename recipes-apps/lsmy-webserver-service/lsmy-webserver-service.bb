SUMMARY = "LSMY system provisioning and application bootstrap services"
DESCRIPTION = "Provisioning and bootstrap services for the LSMY IoT and AI monitoring system. \
This package provides a lightweight web-based provisioning interface using BusyBox httpd \
and a Python WebSocket backend to support initial device configuration such as WiFi setup."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://github.com/nguyenthinhthanh/IoT-AI-Laboratory-Safety-System-on-CoreIoT-with-Yocto-Linux.git;branch=main;protocol=https \
           file://provision-web-frontend.service \
           file://provision-web-backend.service"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

inherit systemd

RDEPENDS:${PN} += " \
	busybox \
    python3-core \
    python3-json \
    python3-websockets \
"

do_install() {
	install -d ${D}${systemd_system_unitdir}
		
    install -m 0644 \
        ${WORKDIR}/provision-web-frontend.service \
        ${D}${systemd_system_unitdir}/provision-web-frontend.service

    install -d ${D}${bindir}
    install -m 0755 \
        ${S}/src/lsmy-webserver-service/provision-web-backend.py \
        ${D}${bindir}/provision-web-backend

    install -d ${D}${systemd_system_unitdir}
    install -m 0644 \
        ${WORKDIR}/provision-web-backend.service \
        ${D}${systemd_system_unitdir}/provision-web-backend.service
}

SYSTEMD_SERVICE:${PN} = " \
	provision-web-frontend.service \
    provision-web-backend.service \
"

FILES:${PN} += " \
    ${bindir}/provision-web-backend \
    ${systemd_system_unitdir}/provision-web-frontend.service \
    ${systemd_system_unitdir}/provision-web-backend.service \
"