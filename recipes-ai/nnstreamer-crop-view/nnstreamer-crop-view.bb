SUMMARY = "GStreamer cropview plugin"
DESCRIPTION = "Custom GstBaseTransform plugin to view crop_decode output"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://github.com/nguyenthinhthanh/IoT-AI-Laboratory-Safety-System-on-CoreIoT-with-Yocto-Linux.git;branch=main;protocol=https"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git/ai/nnstreamer-crop-view"

DEPENDS += "gstreamer1.0 gstreamer1.0-plugins-base nnstreamer glib-2.0"

RDEPENDS:${PN} += " \
    gstreamer1.0 \
    gstreamer1.0-plugins-base \
    nnstreamer \
"

inherit pkgconfig

do_compile() {
    GST_FLAGS=`pkg-config --cflags --libs gstreamer-1.0 gstreamer-base-1.0`
    NNS_FLAGS=`pkg-config --cflags --libs nnstreamer`
    GLIB_FLAGS=`pkg-config --cflags --libs glib-2.0`

    ${CC} ${CFLAGS} ${LDFLAGS} -fPIC -shared crop_view.c \
        -o libgstcropview.so \
        $GST_FLAGS $NNS_FLAGS $GLIB_FLAGS
}

do_install() {
    install -d ${D}${libdir}/gstreamer-1.0

    install -m 0755 libgstcropview.so \
        ${D}${libdir}/gstreamer-1.0/
}

FILES:${PN} += "${libdir}/gstreamer-1.0/libgstcropview.so"
