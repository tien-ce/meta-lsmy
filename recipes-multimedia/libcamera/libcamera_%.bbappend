# Enable GStreamer plugin for libcamera (libcamerasrc)
PACKAGECONFIG:append = " gst"

# Enable Raspberry Pi pipeline, this will also build IPA
EXTRA_OEMESON = "\
    -Dpipelines=raspberrypi \
    -Dipas=raspberrypi \
    -Dv4l2=true \
    -Dcam=enabled \
    -Dgstreamer=enabled \
    -Dlc-compliance=disabled \
    -Dtest=false \
    -Ddocumentation=disabled \
"
