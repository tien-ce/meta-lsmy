SUMMARY = "Base system utilities for LSMY"
DESCRIPTION = "Basic Linux utilities and system tools"
LICENSE = "MIT"

inherit packagegroup

# ====== SYSTEM UTILITIES ======
# - Basic system tools and libraries
# - Useful for debugging and system inspection
RDEPENDS:${PN} = "\
    kernel-modules \
    nano \
    curl \
    opkg \
    glibc \
    coreutils \
    libatomic \
"