#!/bin/sh

OUTPUT=$ROOTFS/etc/security/baseline.db

echo "OUTPUT=$OUTPUT"

mkdir -p $ROOTFS/etc/security
rm -f $OUTPUT

TARGET_DIRS="$ROOTFS/etc \
             $ROOTFS/usr/bin \
             $ROOTFS/usr/sbin \
             $ROOTFS/usr/lib/python3.12/site-packages/lsmy_app \
             $ROOTFS/usr/lib/python3.12/site-packages/lsmy_webserver \
             $ROOTFS/usr/lib/python3.12/site-packages/lsmy_python_lib"

EXCLUDE_PATTERN="etc/security/baseline.db"
EXCLUDE_PATTERN="$EXCLUDE_PATTERN|etc/fstab"
EXCLUDE_PATTERN="$EXCLUDE_PATTERN|etc/wpa_supplicant"
EXCLUDE_PATTERN="$EXCLUDE_PATTERN|/etc/opkg/lsmy-feed.conf"
EXCLUDE_PATTERN="$EXCLUDE_PATTERN|/etc/opkg/gpg/trustdb.gpg"

TEMP_LOG=$(mktemp)

echo "# --- Statistics ---" >> $OUTPUT
for dir in $TARGET_DIRS; do
    if [ -d "$dir" ]; then
        count=$(find "$dir" -type f | grep -vE "$EXCLUDE_PATTERN" | wc -l)
        if [ -n "$ROOTFS" ]; then
            display_dir=$(echo "$dir" | sed "s#$ROOTFS##")
        else
            display_dir="$dir"
        fi
        echo "# $display_dir: $count files" >> $OUTPUT
    fi
done
echo "# ---------------------------------------" >> $OUTPUT

for dir in $TARGET_DIRS; do
    if [ -d "$dir" ]; then
        find "$dir" -type f -exec sha256sum {} + | grep -vE "$EXCLUDE_PATTERN" >> $TEMP_LOG
    fi
done

if [ -n "$ROOTFS" ]; then
    sed "s#$ROOTFS##g" "$TEMP_LOG" >> "$OUTPUT"
else
    cat "$TEMP_LOG" >> "$OUTPUT"
fi

rm -f $TEMP_LOG
