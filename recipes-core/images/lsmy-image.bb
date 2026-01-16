SUMMARY = "LSMY Custom Image"
DESCRIPTION = "Custom image for Raspberry Pi 4 with full command line + custom packages"
LICENSE = "MIT"

inherit core-image

# Inherit the behavior of full-cmdline
require recipes-core/images/core-image-full-cmdline.bb