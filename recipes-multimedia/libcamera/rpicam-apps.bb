SUMMARY = "Raspberry Pi Camera applications rpicam-apps"
DESCRIPTION = "A suite of lightweight C++ applications to test and use libcamera. \
Includes rpicam-hello, rpicam-still, rpicam-vid, and rpicam-raw. \
This is the renamed version of the former libcamera-apps."
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://license.txt;md5=a0013d1b383d72ba4bdc5b750e7d1d77"

SRC_URI = "git://github.com/raspberrypi/rpicam-apps.git;protocol=https;branch=main"
# Tag v1.5.0
SRCREV = "49344f2a8d1817558d4e6463032fcf11be618b38" 

S = "${WORKDIR}/git"

DEPENDS = "libcamera boost libpng jpeg tiff libexif libx11 libepoxy libdrm"

inherit meson pkgconfig

EXTRA_OEMESON = " \
    -Dpreview=enabled \
    -Ddrm=enabled \
    -Dx11=enabled \
    -Dqt=disabled \
    -Dopencv=disabled \
    -Dtflite=disabled \
    -Dhailo=disabled \
"

FILES:${PN} += "${bindir}/*"