SUMMARY = "LSMY security agent service"
DESCRIPTION = "A security monitoring agent for LSMY. It monitors system processes, packages against a whitelist, manages security baselines."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://github.com/nguyenthinhthanh/IoT-AI-Laboratory-Safety-System-on-CoreIoT-with-Yocto-Linux.git;branch=main;protocol=https \
           file://security-agent.service \
           file://gen_whitelist.sh \
           file://gen_baseline.sh \
           file://gen_gold_backup.sh"

SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

inherit systemd

RDEPENDS:${PN} += " \
    python3-core \
    baseline \
    gold-backup \
    whitelist \
"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 \
        ${S}/src/lsmy-security-agent/lsmy-security-agent.py \
        ${D}${bindir}/lsmy-security-agent

    install -d ${D}${systemd_system_unitdir}
    install -m 0644 \
        ${WORKDIR}/security-agent.service \
        ${D}${systemd_system_unitdir}/security-agent.service
}

pkg_postinst_ontarget:${PN} () {
    if [ -e /usr/lib/systemd/system/security-agent.service ]; then
        chattr +i /usr/lib/systemd/system/security-agent.service
        chattr +i /bin/lsmy-security-agent
    fi

    if [ -e /etc/security/baseline.db ]; then
        chattr +i /etc/security/baseline.db
    fi

    if [ -e /etc/security/gold_backup.tar.gz ]; then
        chattr +i /etc/security/gold_backup.tar.gz
    fi

    if [ -e /etc/security/whitelist.txt ]; then
        chattr +i /etc/security/whitelist.txt
    fi
}

do_lsmy_security() {
    bbwarn "=== Generating LSMY security ==="

    export ROOTFS=${IMAGE_ROOTFS}
    export MANIFEST_FILE="${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.manifest"
    export EXTRAS="${@d.getVar('LSMY_OPKG_WHITELIST_ITEMS', True) or ''}"

    sh ${WORKDIR}/gen_whitelist.sh || bbfatal "whitelist failed"
    sh ${WORKDIR}/gen_baseline.sh || bbfatal "baseline failed"
    sh ${WORKDIR}/gen_gold_backup.sh || bbfatal "backup failed"
}

addtask lsmy_security after do_image_complete before do_build

SYSTEMD_SERVICE:${PN} = "security-agent.service"
# SYSTEMD_AUTO_ENABLE:${PN} = "enable"
SYSTEMD_AUTO_ENABLE:${PN} = "disable"

FILES:${PN} += " \
    ${bindir}/lsmy-security-agent \
    ${systemd_system_unitdir}/security-agent.service \
"