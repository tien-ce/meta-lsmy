SUMMARY = "Raspberry Pi Camera applications rpicam-apps"
DESCRIPTION = "A suite of lightweight C++ applications to test and use libcamera. \
Includes rpicam-hello, rpicam-still, rpicam-vid, and rpicam-raw. \
This is the renamed version of the former libcamera-apps."
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://license.txt;md5=2ee03331899745388c4d7ec682c3008f"

SRC_URI = "git://github.com/raspberrypi/rpicam-apps.git;protocol=https;branch=main"
# Tag v1.5.0
SRCREV = "49344f2a8d1817558d4e6463032fcf11be618b38" 

S = "${WORKDIR}/git"

DEPENDS = "libcamera boost libpng jpeg tiff libexif libx11 libepoxy"

inherit cmake pkgconfig

EXTRA_OECMAKE = " \
    -DENABLE_DRM=POST \
    -DENABLE_X11=ON \
    -DENABLE_QT=OFF \
    -DENABLE_OPENCV=OFF \
    -DENABLE_TFLITE=OFF \
"

FILES:${PN} += "${bindir}/*"