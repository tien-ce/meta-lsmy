SUMMARY = "Network and WiFi support for LSMY"
DESCRIPTION = "Network tools and WiFi firmware for Raspberry Pi"
LICENSE = "MIT"

inherit packagegroup

# ====== NETWORK & WIFI ======
# - WiFi firmware (Broadcom BCM43455)
# - Network configuration and management tools
RDEPENDS:${PN} = "\
    linux-firmware-bcm43455 \
    wpa-supplicant \
    dhcpcd \
    iw \
    iproute2 \
"