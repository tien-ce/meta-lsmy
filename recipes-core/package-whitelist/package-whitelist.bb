SUMMARY = "LSMY Whitelist Packages for OPKG management"
DESCRIPTION = "A collection of essential packages intended for use with OPKG for automatic updates"
LICENSE = "MIT"

inherit packagegroup

# ====== PACKAGE WHITELIST ======
RDEPENDS:${PN} = " \
    nano \
    htop \
    rsync \
    zip \
    unzip \
    tree \
    procps \
    logrotate \
"
