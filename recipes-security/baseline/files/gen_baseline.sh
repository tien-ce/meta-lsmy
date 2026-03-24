#!/bin/sh

OUTPUT=${IMAGE_ROOTFS}/etc/security/baseline.db

mkdir -p ${IMAGE_ROOTFS}/etc/security
rm -f $OUTPUT

TARGET_DIRS="${IMAGE_ROOTFS}/etc \
             ${IMAGE_ROOTFS}/sbin \
             ${IMAGE_ROOTFS}/usr/bin \
             ${IMAGE_ROOTFS}/usr/sbin"

TEMP_LOG=$(mktemp)

echo "# --- Statistics (Folder: File Count) ---" >> $OUTPUT
for dir in $TARGET_DIRS; do
    if [ -d "$dir" ]; then
        count=$(find "$dir" -type f | wc -l)
        display_dir=$(echo "$dir" | sed "s#${IMAGE_ROOTFS}##")
        echo "# $display_dir: $count files" >> $OUTPUT
    fi
done
echo "# ---------------------------------------" >> $OUTPUT

for dir in $TARGET_DIRS; do
    if [ -d "$dir" ]; then
        find "$dir" -type f -exec sha256sum {} + >> $TEMP_LOG
    fi
done

sed "s#${IMAGE_ROOTFS}##g" $TEMP_LOG >> $OUTPUT

rm -f $TEMP_LOG
