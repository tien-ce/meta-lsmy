SUMMARY = "Embedded system interfaces for LSMY"
DESCRIPTION = "Core tools and libraries for embedded hardware interfaces and system integration"
LICENSE = "MIT"

inherit packagegroup

# ====== EMBEDDED & HARDWARE INTERFACES ======
# - Hardware communication tools
# - Python bindings for embedded interfaces
RDEPENDS:${PN} = "\
    libgpiod \
    python3-gpiod \
"
