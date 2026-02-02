# Extend upstream Raspberry Pi kernel

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

# Add kernel config fragments
SRC_URI += " \
    file://rpi-i2c.cfg \
    file://camera.cfg \
    file://blacklist.conf \
"

do_install:append() {
    install -d ${D}${sysconfdir}/modprobe.d
    install -m 0644 ${WORKDIR}/blacklist.conf \
        ${D}${sysconfdir}/modprobe.d/blacklist.conf
}
