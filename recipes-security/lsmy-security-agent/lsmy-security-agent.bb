SUMMARY = "LSMY security agent service"
DESCRIPTION = "A security monitoring agent for LSMY. It monitors system processes, packages against a whitelist, manages security baselines."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://github.com/nguyenthinhthanh/IoT-AI-Laboratory-Safety-System-on-CoreIoT-with-Yocto-Linux.git;branch=main;protocol=https \
           file://security-agent.service"

SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

inherit systemd

RDEPENDS:${PN} += " \
    python3-core \
    baseline \
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

SYSTEMD_SERVICE:${PN} = "security-agent.service"
# SYSTEMD_AUTO_ENABLE:${PN} = "enable"

FILES:${PN} += " \
    ${bindir}/lsmy-security-agent \
    ${systemd_system_unitdir}/security-agent.service \
"