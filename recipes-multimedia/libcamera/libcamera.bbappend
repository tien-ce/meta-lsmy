# Enable Raspberry Pi pipeline, this will also build IPA
EXTRA_OEMESON = "\
    -Dpipelines=raspberrypi \
    -Dv4l2=true \
    -Dcam=enabled \
    -Dlc-compliance=disabled \
    -Dtest=false \
    -Ddocumentation=disabled \
"
