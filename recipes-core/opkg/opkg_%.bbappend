do_install:append() {
    echo "option check_signature 1" >> ${D}${sysconfdir}/opkg/opkg.conf
    echo "option signature_ca_file ${sysconfdir}/opkg/keys/lsmy-pub.key" >> ${D}${sysconfdir}/opkg/opkg.conf
}