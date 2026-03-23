FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://99-lsmy-networkd.preset \
            file://10-lsmy-journal.conf"

do_install:append() {
    install -d ${D}${sysconfdir}/systemd/journald.conf.d/
    install -m 0644 ${WORKDIR}/10-lsmy-journal.conf ${D}${sysconfdir}/systemd/journald.conf.d/
}
