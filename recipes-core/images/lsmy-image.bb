SUMMARY = "LSMY Custom Image"
DESCRIPTION = "Custom image for Raspberry Pi 4 with full command line + custom packages"
LICENSE = "MIT"

inherit core-image

# Inherit the behavior of full-cmdline
require recipes-extended/images/core-image-full-cmdline.bb

############################################
# DEVELOP CUSTOM CONFIGURATION
# Project: LSMY Lab Monitoring
# Author : Nguyen Thinh Thanh
############################################

# ====== SYSTEM FEATURE STACK ======
IMAGE_INSTALL += "\
    packagegroup-lsmy-base \
    packagegroup-lsmy-network \
    packagegroup-lsmy-python-core \
    packagegroup-lsmy-embedded \
    packagegroup-lsmy-iot \
    packagegroup-lsmy-computer-vision \
    packagegroup-lsmy-ai \
    packagegroup-lsmy-apps \
"

# ====== SYSTEM UTILITIES ======
# - Basic system tools and libraries
# - Useful for debugging and system inspection
IMAGE_INSTALL:append = " \
    glibc \
    libatomic \
    libx11 \
    libgpiod \
    libgpiod-tools \
"

# ====== REMOTE ACCESS (SSH) ======
# Enable OpenSSH server for remote login and management
IMAGE_FEATURES += "ssh-server-openssh"

# ====== PACKAGE MANAGER ======
IMAGE_FEATURES += "package-management"

# Disable selected brcmfmac firmware features for WiFi stability
CMDLINE:append = " brcmfmac.feature_disable=0x82000"

############################################
