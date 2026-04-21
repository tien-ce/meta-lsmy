SUMMARY = "GStreamer cropdecode plugin"
DESCRIPTION = "Custom GstBaseTransform plugin to decode tensor_crop output"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://github.com/nguyenthinhthanh/IoT-AI-Laboratory-Safety-System-on-CoreIoT-with-Yocto-Linux.git;branch=main;protocol=https"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git/ai/nnstreamer-crop"

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

    ${CC} ${CFLAGS} ${LDFLAGS} -fPIC -shared crop_decode.c \
        -o libgstcropdecode.so \
        $GST_FLAGS $NNS_FLAGS $GLIB_FLAGS
}

do_install() {
    install -d ${D}${libdir}/gstreamer-1.0

    install -m 0755 libgstcropdecode.so \
        ${D}${libdir}/gstreamer-1.0/
}

FILES:${PN} += "${libdir}/gstreamer-1.0/libgstcropdecode.so"
