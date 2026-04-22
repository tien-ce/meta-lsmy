SUMMARY = "AI and data analytics stack for LSMY"
DESCRIPTION = "Libraries supporting artificial intelligence and data analytics applications"
LICENSE = "MIT"

inherit packagegroup

# ====== PYTHON AI / NUMERICAL ======
# - Numerical computation
# - OpenCV Python bindings
RDEPENDS:${PN} = "\
    nnstreamer-blaze \
    nnstreamer-face-mesh \
    nnstreamer-crop \
    nnstreamer-crop-view \
"
