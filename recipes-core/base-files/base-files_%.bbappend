do_install:append() {
    install -d ${D}/data
    
    echo "LABEL=data  /data  ext4  defaults  0  2" >> ${D}${sysconfdir}/fstab
}