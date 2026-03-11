CXXFLAGS += "-include cstdint"

EXTRA_OECMAKE:append = " \
    -DTFLITE_ENABLE_TOOLS=ON \
    -DTFLITE_ENABLE_BENCHMARK=ON \
    -DTFLITE_BUILD_SHARED_LIB=ON \
    -DTFLITE_BUILD_BENCHMARK_MODEL=ON \
"

OECMAKE_TARGET_COMPILE:append = " benchmark_model"

do_install:append() {
    if [ -f ${B}/tools/benchmark/benchmark_model ]; then
        install -d ${D}${bindir}
        install -m 0755 ${B}/tools/benchmark/benchmark_model ${D}${bindir}/
    else
        bbwarn "benchmark_model NOT found in ${B}/tools/benchmark/"
    fi
}

FILES:${PN} += "${bindir}/benchmark_model"
