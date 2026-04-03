SUMMARY = "Install GPG public key for opkg signature verification"
DESCRIPTION = "Install GPG public key for opkg signature verification."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://lsmy-pub.key"

S = "${WORKDIR}"

inherit systemd

DEPENDS += "gnupg-native"

do_install() {
    install -d ${D}${sysconfdir}/opkg/keys
    install -m 0644 ${WORKDIR}/lsmy-pub.key ${D}${sysconfdir}/opkg/keys/lsmy-pub.key

    install -d ${D}${sysconfdir}/opkg/gpg
    gpg --batch --homedir ${D}${sysconfdir}/opkg/gpg --import ${WORKDIR}/lsmy-pub.key
    echo "B9D89B17D0CB8A514FBC498612879EE7E411807D:6:" | gpg --batch --homedir ${D}${sysconfdir}/opkg/gpg --import-ownertrust

    chmod 644 ${D}${sysconfdir}/opkg/gpg/pubring.kbx
    chmod 644 ${D}${sysconfdir}/opkg/gpg/trustdb.gpg

    rm -rf ${D}${sysconfdir}/opkg/gpg/S.*
    rm -f ${D}${sysconfdir}/opkg/gpg/*.lock
}

FILES:${PN} += " \
    ${sysconfdir}/opkg/keys/lsmy-pub.key \
    ${sysconfdir}/opkg/gpg/* \
"
