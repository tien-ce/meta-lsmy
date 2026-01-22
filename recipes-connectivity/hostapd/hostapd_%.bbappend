FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += " \
    file://hostapd-override.conf \
"

inherit systemd

SYSTEMD_SERVICE:${PN} = "hostapd.service"

do_install:append() {
    # systemd override
    install -d ${D}${systemd_system_unitdir}/hostapd.service.d
    install -m 0644 ${WORKDIR}/hostapd-override.conf \
        ${D}${systemd_system_unitdir}/hostapd.service.d/override.conf
}

FILES:${PN} += " \
    ${systemd_system_unitdir}/hostapd.service.d \
"
