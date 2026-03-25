#!/bin/sh

OUTPUT=$ROOTFS/etc/security/baseline.db

echo "OUTPUT=$OUTPUT"

mkdir -p $ROOTFS/etc/security
rm -f $OUTPUT

TARGET_DIRS="$ROOTFS/etc \
             $ROOTFS/sbin \
             $ROOTFS/usr/bin \
             $ROOTFS/usr/sbin"

EXCLUDE_PATTERN="etc/security/baseline.db"
EXCLUDE_PATTERN="$EXCLUDE_PATTERN|etc/fstab"
EXCLUDE_PATTERN="$EXCLUDE_PATTERN|etc/wpa_supplicant"

TEMP_LOG=$(mktemp)

echo "# --- Statistics (Folder: File Count) ---" >> $OUTPUT
for dir in $TARGET_DIRS; do
    if [ -d "$dir" ]; then
        count=$(find "$dir" -type f | grep -vE "$EXCLUDE_PATTERN" | wc -l)
        display_dir=$(echo "$dir" | sed "s#$ROOTFS##")
        echo "# $display_dir: $count files" >> $OUTPUT
    fi
done
echo "# ---------------------------------------" >> $OUTPUT

for dir in $TARGET_DIRS; do
    if [ -d "$dir" ]; then
        find "$dir" -type f -exec sha256sum {} + | grep -vE "$EXCLUDE_PATTERN" >> $TEMP_LOG
    fi
done

sed "s#$ROOTFS##g" $TEMP_LOG >> $OUTPUT

rm -f $TEMP_LOG
