SUMMARY = "Camera and Computer Vision stack for LSMY"
DESCRIPTION = "OpenCV libraries and dependencies for camera and vision applications"
LICENSE = "MIT"

inherit packagegroup

# ====== CAMERA & COMPUTER VISION ======
# - OpenCV runtime libraries
RDEPENDS:${PN} = "\
    v4l-utils \
    libv4l \
    libcamera \
    libcamera-apps \
    gstreamer1.0 \
    gstreamer1.0-libcamera \
    gstreamer1.0-plugins-base \
    gstreamer1.0-plugins-good \
    gstreamer1.0-plugins-bad \
    opencv \
    xauth \
"
