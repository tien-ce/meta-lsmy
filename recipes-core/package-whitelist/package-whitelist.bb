SUMMARY = "LSMY Whitelist Packages for OPKG management"
DESCRIPTION = "A collection of essential packages intended for use with OPKG for automatic updates"
LICENSE = "MIT"

inherit packagegroup

# ====== PACKAGE WHITELIST ======
LSMY_OPKG_WHITELIST_ITEMS = " \
    nano \
    htop \
    rsync \
    zip \
    unzip \
    tree \
    procps \
    logrotate \
"

RDEPENDS:${PN} = "${LSMY_OPKG_WHITELIST_ITEMS}"
