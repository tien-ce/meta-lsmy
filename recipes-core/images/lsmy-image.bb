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

# ====== REMOTE ACCESS (SSH) ======
# Enable OpenSSH server for remote login and management
IMAGE_FEATURES += "ssh-server-openssh"
CMDLINE:append = " brcmfmac.feature_disable=0x82000"

# ====== NETWORK & WIFI ======
# - WiFi firmware (Broadcom BCM43455)
# - Network configuration and management tools
IMAGE_INSTALL:append = " \
    linux-firmware-bcm43455 \
    wpa-supplicant \
    dhcpcd \
    iw \
    iproute2 \
"

# ====== SYSTEM UTILITIES ======
# - Basic system tools and libraries
# - Useful for debugging and system inspection
IMAGE_INSTALL:append = " \
    kernel-modules \
    nano \
    curl \
    opkg \
    glibc \
    coreutils \
    libatomic \
"

# ====== I2C & HARDWARE INTERFACES ======
# - Tools for communicating with I2C sensors and peripherals
IMAGE_INSTALL:append = " \
    i2c-tools \
"

# ====== CAMERA & COMPUTER VISION ======
# - OpenCV libraries for image processing and camera applications
#IMAGE_INSTALL:append = " \
#    opencv \
#"

# ====== X11 / DISPLAY SUPPORT ======
# - Required for X11 authentication and GUI-based applications
#IMAGE_INSTALL:append = " \
#    xauth \
#"

# ====== PYTHON CORE ======
# - Python interpreter and package manager
#IMAGE_INSTALL:append = " \
#    python3 \
#    python3-pip \
#"

# ====== PYTHON HARDWARE INTERFACES ======
# - Access hardware interfaces from Python
#IMAGE_INSTALL:append = " \
#    python3-smbus \
#    python3-periphery \
#    python3-pyserial \
#"

# ====== PYTHON NETWORK & IOT ======
# - HTTP, MQTT, WebSocket communication
#IMAGE_INSTALL:append = " \
#    python3-requests \
#    python3-paho-mqtt \
#    python3-websocket-client \
#"

# ====== PYTHON AI / NUMERICAL ======
# - Numerical computation and OpenCV Python bindings
#IMAGE_INSTALL:append = " \
#    python3-numpy \
#    python3-opencv \
#"

############################################
