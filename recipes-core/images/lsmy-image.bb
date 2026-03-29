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

# ====== SYSTEM CONFIGURATION ======
WKS_FILE = "sdimage-raspberrypi-lsmy.wks"

# ====== SYSTEM POSTPROCESS ======
do_generate_baseline() {
    # bbwarn "=== GENERATE BASELINE  ==="

    export ROOTFS=${IMAGE_ROOTFS}
    export MANIFEST_FILE="${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.manifest"
    export EXTRAS="${@d.getVar('LSMY_OPKG_WHITELIST_ITEMS', True) or ''}"
    sh ${IMAGE_ROOTFS}/usr/bin/gen_whitelist.sh
    sh ${IMAGE_ROOTFS}/usr/bin/gen_baseline.sh
    sh ${IMAGE_ROOTFS}/usr/bin/gen_gold_backup.sh
}

addtask generate_baseline after do_image_complete before do_build

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
    libgpiod \
    libgpiod-tools \
"

# ====== REMOTE ACCESS (SSH) ======
# Enable OpenSSH server for remote login and management
IMAGE_FEATURES += "ssh-server-openssh"

# ====== PACKAGE MANAGER ======
IMAGE_FEATURES += "package-management"
# IMAGE_FEATURES:remove = "package-management"

# ====== OS SECURITY ======
# Enable read-only rootfs
# IMAGE_FEATURES += "read-only-rootfs"

# Disable debug features
# IMAGE_FEATURES:remove = "debug-tweaks"
# EXTRA_IMAGE_FEATURES:remove = "debug-tweaks"

# Disable selected brcmfmac firmware features for WiFi stability
CMDLINE:append = " brcmfmac.feature_disable=0x82000"

############################################
