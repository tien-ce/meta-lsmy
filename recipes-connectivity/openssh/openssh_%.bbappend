do_install:append() {
    sed -i 's/#X11Forwarding no/X11Forwarding yes/' \
        ${D}${sysconfdir}/ssh/sshd_config

    sed -i 's/#X11DisplayOffset 10/X11DisplayOffset 10/' \
        ${D}${sysconfdir}/ssh/sshd_config

    sed -i 's/#X11UseLocalhost yes/X11UseLocalhost yes/' \
        ${D}${sysconfdir}/ssh/sshd_config
}