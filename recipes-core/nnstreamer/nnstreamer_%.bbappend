DEPENDS:append = " tensorflow-lite"

EXTRA_OEMESON:append = " -Denable-tflite=true"

FILES:${PN}:append = " ${libdir}/nnstreamer/filters/*.so"