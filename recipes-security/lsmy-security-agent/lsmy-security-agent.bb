SUMMARY = "LSMY security agent service"
DESCRIPTION = "A security monitoring agent for LSMY. It monitors system processes, packages against a whitelist, manages security baselines."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://github.com/nguyenthinhthanh/IoT-AI-Laboratory-Safety-System-on-CoreIoT-with-Yocto-Linux.git;branch=main;protocol=https \
           file://security-agent.service \
           "

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

    if [ -e /bin/gen_baseline.sh ]; then
        chown root:root /bin/gen_baseline.sh
        chmod 700 /bin/gen_baseline.sh
        chattr +i /bin/gen_baseline.sh
    fi

    if [ -e /bin/gen_gold_backup.sh ]; then
        chown root:root /bin/gen_gold_backup.sh
        chmod 700 /bin/gen_gold_backup.sh
        chattr +i /bin/gen_gold_backup.sh
    fi

    if [ -e /bin/gen_whitelist.sh ]; then
        chown root:root /bin/gen_whitelist.sh
        chmod 700 /bin/gen_whitelist.sh
        chattr +i /bin/gen_whitelist.sh
    fi


}

SYSTEMD_SERVICE:${PN} = "security-agent.service"
# SYSTEMD_AUTO_ENABLE:${PN} = "enable"
SYSTEMD_AUTO_ENABLE:${PN} = "disable"

FILES:${PN} += " \
    ${bindir}/lsmy-security-agent \
    ${systemd_system_unitdir}/security-agent.service \
"