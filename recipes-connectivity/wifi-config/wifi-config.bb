SUMMARY = "LSMY WiFi configuration (AP + STA)"
DESCRIPTION = "WiFi configuration files for LSMY system: AP setup, STA setup, and service integration"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = " \
    file://wlan0-ap.network \
    file://wlan0-sta.network \
    file://hostapd.conf \
    file://dnsmasq-ap.conf \
    file://wpa_supplicant.conf \
"

S = "${WORKDIR}"

inherit systemd

RDEPENDS:${PN} = " \
    hostapd \
    dnsmasq \
    wpa-supplicant \
    systemd-networkd \
"

do_install() {
    # systemd-networkd configs
    install -d ${D}/etc/systemd/network
    install -m 0644 ${WORKDIR}/wlan0-ap.network \
        ${D}/etc/systemd/network/wlan0-ap.network
    install -m 0644 ${WORKDIR}/wlan0-sta.network \
        ${D}/etc/systemd/network/wlan0-sta.network

    # Default to STA mode
    ln -sf wlan0-sta.network \
        ${D}/etc/systemd/network/10-wlan0.network

    # hostapd config
    install -d ${D}/etc/hostapd
    install -m 0600 ${WORKDIR}/hostapd.conf \
        ${D}/etc/hostapd/hostapd.conf

    # dnsmasq config for AP mode
    install -d ${D}/etc/dnsmasq.d
    install -m 0644 ${WORKDIR}/dnsmasq-ap.conf \
        ${D}/etc/dnsmasq.d/ap.conf

    # wpa_supplicant config
    install -d ${D}/etc/wpa_supplicant
    install -m 0600 ${WORKDIR}/wpa_supplicant.conf \
        ${D}/etc/wpa_supplicant/wpa_supplicant.conf
}

SYSTEMD_SERVICE:${PN} = " \
    systemd-networkd.service \
"
SYSTEMD_AUTO_ENABLE = "enable"
