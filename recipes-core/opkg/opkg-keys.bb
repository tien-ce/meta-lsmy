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

pkg_postinst_ontarget:${PN} () {
    #!/bin/sh
    echo "Configuring GPG key for OPKG..."
    
    date -s "2026-04-03 15:45:00"    

    opkg-key list    
   
    gpg --homedir /etc/opkg/gpg --import /etc/opkg/keys/lsmy-pub.key
    
    echo "B9D89B17D0CB8A514FBC498612879EE7E411807D:6:" | gpg --homedir /etc/opkg/gpg --import-ownertrust
    
    chmod 644 /etc/opkg/gpg/pubring.kbx
    chmod 644 /etc/opkg/gpg/trustdb.gpg
    
    echo "GPG Key configuration finished!"
}

FILES:${PN} += " \
    ${sysconfdir}/opkg/keys/lsmy-pub.key \
"
