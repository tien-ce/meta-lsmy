SUMMARY = "Camera and Computer Vision stack for LSMY"
DESCRIPTION = "Computer vision libraries and dependencies for camera and vision applications"
LICENSE = "MIT"

inherit packagegroup

# ====== CAMERA & COMPUTER VISION ======
# - Camera and computer vision libraries
RDEPENDS:${PN} = "\
    v4l-utils \
    libv4l \
    libcamera \
    libcamera-apps \
    libcamera-gst \
    gstreamer1.0 \
    gstreamer1.0-plugins-base \
    gstreamer1.0-plugins-good \
    gstreamer1.0-plugins-bad \
    gstreamer1.0-plugins-bad-kms \
    python3-pygobject \
    python3-psutil \
    nnstreamer \
    nnstreamer-tensorflow-lite \
    tensorflow-lite \
    weston \
    weston-init \
    weston-examples \
"
