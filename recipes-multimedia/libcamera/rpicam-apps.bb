SUMMARY = "Raspberry Pi Camera applications rpicam-apps"
DESCRIPTION = "A suite of lightweight C++ applications to test and use libcamera. \
Includes rpicam-hello, rpicam-still, rpicam-vid, and rpicam-raw. \
This is the renamed version of the former libcamera-apps."
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://license.txt;md5=a0013d1b383d72ba4bdc5b750e7d1d77"

SRC_URI = "git://github.com/raspberrypi/rpicam-apps.git;protocol=https;branch=main"
# Tag v1.0.2
SRCREV = "556858ee3d00fecc9c96023e62587f24f13e5c97" 

S = "${WORKDIR}/git"

DEPENDS = "libcamera boost libpng jpeg tiff libexif libx11 libepoxy libdrm"

inherit cmake pkgconfig

do_configure:prepend() {
    find ${S} -name "meson.build" -type f -exec sed -i "s/meson_version.*>=.*/meson_version : '>= 0.61.3',/g" {} +
}

EXTRA_OECMAKE = " \
    -DENABLE_DRM=1 \
    -DENABLE_EGL=1 \
    -DENABLE_QT=0 \
    -DENABLE_OPENCV=0 \
    -DENABLE_TFLITE=0 \
"

FILES:${PN} += "${bindir}/* ${libdir}/*.so"