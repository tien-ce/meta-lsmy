# Using libcamera new version for raspberrypi
SRC_URI = "git://github.com/raspberrypi/libcamera.git;protocol=https;branch=master"
# Tag v0.1.0
SRCREV = "9e3a83c03bab65b745eea2482be78077b409a2b5"

# Enable Raspberry Pi pipeline, this will also build IPA
EXTRA_OEMESON = "\
    -Dpipelines=raspberrypi \
    -Dv4l2=true \
    -Dcam=enabled \
    -Dlc-compliance=disabled \
    -Dtest=false \
    -Ddocumentation=disabled \
"
