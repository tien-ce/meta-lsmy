SUMMARY = "Raspberry Pi Camera applications rpicam-apps"
DESCRIPTION = "A suite of lightweight C++ applications to test and use libcamera. \
Includes rpicam-hello, rpicam-still, rpicam-vid, and rpicam-raw. \
This is the renamed version of the former libcamera-apps."
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://license.txt;md5=a0013d1b383d72ba4bdc5b750e7d1d77"

SRC_URI = "git://github.com/raspberrypi/rpicam-apps.git;protocol=https;branch=main"
# Tag v1.2.0
SRCREV = "4334f5aa0783206e7c331ff36d9729451c601004" 

S = "${WORKDIR}/git"

DEPENDS = "libcamera boost libpng jpeg tiff libexif libx11 libepoxy libdrm"

inherit meson pkgconfig

do_configure:prepend() {
    find ${S} -name "meson.build" -type f -exec sed -i "s/meson_version.*>=.*/meson_version : '>= 0.61.3',/g" {} +
}

EXTRA_OEMESON = " \
    -Denable_drm=enabled \
    -Denable_egl=enabled \
    -Denable_qt=disabled \
    -Denable_opencv=disabled \
    -Denable_tflite=disabled \
    -Denable_hailo=disabled \
    -Dneon_flags=auto \
"

FILES:${PN} += "${bindir}/*"