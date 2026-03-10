PACKAGECONFIG:append = " tensorflow-lite"
PACKAGECONFIG[tensorflow-lite] = "-Dtflite2-support=enabled,-Dtflite2-support=disabled,tensorflow-lite"

DEPENDS:append = " tensorflow-lite"

EXTRA_OEMESON:append = " \
    -Dtflite2-support=enabled \
    -Dskip-tflite-flatbuf-check=true \
"
