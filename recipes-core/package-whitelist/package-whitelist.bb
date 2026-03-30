SUMMARY = "LSMY Whitelist Packages for OPKG management"
DESCRIPTION = "A collection of essential packages intended for use with OPKG for automatic updates"
LICENSE = "MIT"

require recipes-core/include/parameters.inc

inherit packagegroup

RDEPENDS:${PN} = "${LSMY_OPKG_WHITELIST_ITEMS}"
