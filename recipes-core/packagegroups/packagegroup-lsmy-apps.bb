SUMMARY = "LSMY applications"
DESCRIPTION = "Package group for LSMY project applications, providing all custom user-space services and applications required for the target system."
LICENSE = "MIT"

inherit packagegroup

# ====== LSMY APPLICATION ======
# - Hello app for test
RDEPENDS:${PN} = "\
    hello-py \
    hello-c \
    run-lsmy \
    wifi-config \
"
