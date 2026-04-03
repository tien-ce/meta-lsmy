SUMMARY = "Install GPG public key for opkg signature verification"
DESCRIPTION = "Install GPG public key for opkg signature verification."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://lsmy-pub.key"

S = "${WORKDIR}"

inherit systemd

do_install() {
    install -d ${D}${sysconfdir}/opkg/keys
    install -m 0644 ${WORKDIR}/lsmy-pub.key ${D}${sysconfdir}/opkg/keys/lsmy-pub.key
}

FILES:${PN} = "${sysconfdir}/opkg/keys/lsmy-pub.key"
