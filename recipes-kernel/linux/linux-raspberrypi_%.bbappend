# Extend upstream Raspberry Pi kernel

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

# Add kernel config fragments
SRC_URI += " \
    file://rpi-i2c.cfg \
"
