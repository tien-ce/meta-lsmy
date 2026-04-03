SUMMARY = "Set system time to build time at boot"
DESCRIPTION = "Set system time to build time at boot"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://set-build-time.sh"

inherit systemd

do_install() {
    install -d ${D}${bindir}
    sed "s|@BUILD_TIME@|${@time.strftime('%Y-%m-%d %H:%M:%S', time.gmtime())}|" \
        ${WORKDIR}/set-build-time.sh > ${D}${bindir}/set-build-time.sh

    chmod +x ${D}${bindir}/set-build-time.sh
}

pkg_postinst_ontarget:${PN} () {
    #!/bin/sh

    echo "Setting build time..."
    /usr/bin/set-build-time.sh
}

FILES:${PN} += " \
    ${bindir}/set-build-time.sh \
"