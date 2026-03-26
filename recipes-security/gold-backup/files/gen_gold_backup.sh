#!/bin/sh

BACKUP_OUT=$ROOTFS/etc/security/gold_backup.tar.gz

echo "Creating Gold Backup at: $BACKUP_OUT"

mkdir -p $ROOTFS/etc/security
if [ -e "$BACKUP_OUT" ]; then
    chattr -i "$BACKUP_OUT" 2>/dev/null
    rm -f "$BACKUP_OUT"
fi

TARGET_DIRS="/etc \
             /usr/bin \
             /usr/sbin \
             /usr/lib/python3.12/site-packages/lsmy_app \
             /usr/lib/python3.12/site-packages/lsmy_webserver \
             /usr/lib/python3.12/site-packages/lsmy_python_lib"

EXCLUDE_PATTERN="etc/security/baseline.db"
EXCLUDE_PATTERN="$EXCLUDE_PATTERN|etc/fstab"
EXCLUDE_PATTERN="$EXCLUDE_PATTERN|etc/wpa_supplicant"

FILE_LIST_TEMP=$(mktemp)

for item in $TARGET_DIRS; do
    FULL_PATH="${ROOTFS}${item}"
    
    if [ -e "$FULL_PATH" ]; then
        find "$FULL_PATH" -type f | grep -vE "$EXCLUDE_PATTERN" | sed "s#$ROOTFS##" >> $FILE_LIST_TEMP
    fi
done

tar -cvpzf "$BACKUP_OUT" -C "$ROOTFS" -T "$FILE_LIST_TEMP"

rm -f "$FILE_LIST_TEMP"