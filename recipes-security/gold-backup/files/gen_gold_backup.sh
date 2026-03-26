#!/bin/sh

BACKUP_OUT=$ROOTFS/etc/security/gold_backup.tar.gz

echo "Creating Gold Backup at: $BACKUP_OUT"

mkdir -p $ROOTFS/etc/security
if [ -e "$BACKUP_OUT" ]; then
    chattr -i "$BACKUP_OUT" 2>/dev/null
    rm -f "$BACKUP_OUT"
fi

TARGET_DIRS="$ROOTFS/etc \
             $ROOTFS/usr/bin \
             $ROOTFS/usr/sbin \
             $ROOTFS/usr/lib/python3.12/site-packages/lsmy_app \
             $ROOTFS/usr/lib/python3.12/site-packages/lsmy_webserver \
             $ROOTFS/usr/lib/python3.12/site-packages/lsmy_python_lib"

EXCLUDE_PATTERN="etc/security/baseline.db"
EXCLUDE_PATTERN="$EXCLUDE_PATTERN|etc/fstab"
EXCLUDE_PATTERN="$EXCLUDE_PATTERN|etc/wpa_supplicant"

FILE_LIST_TEMP=$(mktemp)

for dir in $TARGET_DIRS; do
    if [ -d "$dir" ]; then
        find "$dir" -type f | grep -vE "$EXCLUDE_PATTERN" | sed "s#$ROOTFS/##g" >> $FILE_LIST_TEMP
    fi
done

tar -cvpzf "$BACKUP_OUT" -C "$ROOTFS" -T "$FILE_LIST_TEMP"

rm -f "$FILE_LIST_TEMP"
