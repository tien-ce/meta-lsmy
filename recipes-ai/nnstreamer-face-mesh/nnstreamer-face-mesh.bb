SUMMARY = "NNStreamer custom filter: face_mesh_decode"
DESCRIPTION = "Custom tensor_filter for FaceMesh decode"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://github.com/nguyenthinhthanh/IoT-AI-Laboratory-Safety-System-on-CoreIoT-with-Yocto-Linux.git;branch=main;protocol=https"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git/ai/nnstreamer-face-mesh"

DEPENDS += "nnstreamer glib-2.0"
inherit pkgconfig

do_compile() {
    GLIB_FLAGS=`pkg-config --cflags --libs glib-2.0`
    NNS_FLAGS=`pkg-config --cflags --libs nnstreamer`
    ${CC} ${CFLAGS} ${LDFLAGS} -fPIC -shared face_mesh_decode.c \
        -o libnnstreamer_filter_face_mesh_decode.so \
        $GLIB_FLAGS $NNS_FLAGS
}

do_install() {
    install -d ${D}/usr/lib/nnstreamer/filters
    install -m 0755 libnnstreamer_filter_face_mesh_decode.so \
        ${D}/usr/lib/nnstreamer/filters/
}

FILES:${PN} += "/usr/lib/nnstreamer/filters/libnnstreamer_filter_face_mesh_decode.so"
