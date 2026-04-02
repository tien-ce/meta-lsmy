SUMMARY = "LSMY package management and utility script"
DESCRIPTION = "A custom shell script for managing LSMY system components and package configurations."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://mpkg.sh \
           file://mpkg-alias.sh \
           "

inherit systemd

RDEPENDS:${PN} += " \
    socat \
"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/mpkg.sh ${D}${bindir}/mpkg

    install -d ${D}${sysconfdir}/profile.d
    install -m 0644 ${WORKDIR}/mpkg-alias.sh ${D}${sysconfdir}/profile.d/mpkg-alias.sh
}

FILES:${PN} += " \
    ${bindir}/mpkg \
    ${sysconfdir}/profile.d/mpkg-alias.sh \
"