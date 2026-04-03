PACKAGECONFIG:append = " gpg"

do_install:append() {
    echo "option check_signature 1" >> ${D}${sysconfdir}/opkg/opkg.conf
    echo "option signature_type gpg-asc" >> ${D}${sysconfdir}/opkg/opkg.conf
}
