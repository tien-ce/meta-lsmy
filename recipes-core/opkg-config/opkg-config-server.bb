SUMMARY = "OPKG Feed configuration for LSMY system"
DESCRIPTION = "Configures /etc/opkg/ to point to the LSMY remote package repository for automated software deployment."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

S = "${WORKDIR}"

def get_host_ip():
    import os
    arg = "hostname -I | awk '{print $1}'"
    import subprocess
    try:
        ip = subprocess.check_output(arg, shell=True).decode('utf-8').strip()
        # bb.plain("---------------------------------------------------------")
        # bb.plain("LSMY Server: http://%s:8000" % ip)
        # bb.plain("---------------------------------------------------------")
        return ip
    except:
        return "127.0.0.1"

SERVER_IP ?= "${@get_host_ip()}"
SERVER_PORT = "8000"

do_install() {
    install -d ${D}${sysconfdir}/opkg
    
    echo "src/gz all http://${SERVER_IP}:${SERVER_PORT}/all" > ${D}${sysconfdir}/opkg/lsmy-feed.conf
    echo "src/gz cortexa72 http://${SERVER_IP}:${SERVER_PORT}/cortexa72" >> ${D}${sysconfdir}/opkg/lsmy-feed.conf
    echo "src/gz raspberrypi4_lsmy http://${SERVER_IP}:${SERVER_PORT}/raspberrypi4_lsmy" >> ${D}${sysconfdir}/opkg/lsmy-feed.conf
}

FILES:${PN} = "${sysconfdir}/opkg/lsmy-feed.conf"
