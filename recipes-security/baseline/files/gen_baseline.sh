#!/bin/sh

OUTPUT=$ROOTFS/etc/security/baseline.db

echo "OUTPUT=$OUTPUT"

mkdir -p $ROOTFS/etc/security
rm -f $OUTPUT

TARGET_DIRS="$ROOTFS/etc \
             $ROOTFS/sbin \
             $ROOTFS/usr/bin \
             $ROOTFS/usr/sbin"

EXCLUDE_ARGS="-not -path '*/etc/security/baseline.db' \
              -not -path '*/etc/fstab' \
              -not -path '*/etc/wpa_supplicant.conf'"

TEMP_LOG=$(mktemp)

echo "# --- Statistics (Folder: File Count) ---" >> $OUTPUT
for dir in $TARGET_DIRS; do
    if [ -d "$dir" ]; then
        count=$(find "$dir" -type f | wc -l)
        display_dir=$(echo "$dir" | sed "s#$ROOTFS##")
        echo "# $display_dir: $count files" >> $OUTPUT
    fi
done
echo "# ---------------------------------------" >> $OUTPUT

for dir in $TARGET_DIRS; do
    if [ -d "$dir" ]; then
        find "$dir" -type f $EXCLUDE_ARGS -exec sha256sum {} + >> $TEMP_LOG
    fi
done

sed "s#$ROOTFS##g" $TEMP_LOG >> $OUTPUT

rm -f $TEMP_LOG
