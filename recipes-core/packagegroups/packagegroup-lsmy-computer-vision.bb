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
    opencv \
    xauth \
"
