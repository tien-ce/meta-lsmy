SUMMARY = "NNStreamer custom filter: blaze_decode"
DESCRIPTION = "Custom tensor_filter for BlazeFace decode"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://github.com/nguyenthinhthanh/IoT-AI-Laboratory-Safety-System-on-CoreIoT-with-Yocto-Linux.git;branch=main;protocol=https"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git/ai/nnstreamer-blaze"

DEPENDS += "nnstreamer glib-2.0"
inherit pkgconfig

do_compile() {
    ${CC} ${CFLAGS} -I${STAGING_INCDIR}/nnstreamer -fPIC -shared blaze_decode.c \
        -o libnnstreamer_customfilter_blaze_decode.so \
        ${LDFLAGS} -lnnstreamer -lglib-2.0
}

do_install() {
    install -d ${D}/usr/lib/nnstreamer/customfilters
    install -m 0755 libnnstreamer_customfilter_blaze_decode.so \
        ${D}/usr/lib/nnstreamer/customfilters/
}

FILES:${PN} += "/usr/lib/nnstreamer/customfilters/libnnstreamer_customfilter_blaze_decode.so"
