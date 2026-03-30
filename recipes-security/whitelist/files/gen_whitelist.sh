#!/bin/sh

OUTPUT_DIR="$ROOTFS/etc/security"
OUTPUT_FILE="$OUTPUT_DIR/whitelist.txt"

echo "Generating package whitelist baseline..."
mkdir -p "$OUTPUT_DIR"

if [ -s "$MANIFEST_FILE" ]; then
    awk '{print $1}' "$MANIFEST_FILE" > "$OUTPUT_FILE"
else
    echo "Error: Manifest file is empty or missing at $MANIFEST_FILE"
    exit 1
fi

for pkg in $EXTRAS; do
    echo "$pkg" >> "$OUTPUT_FILE"
done

sort -u "$OUTPUT_FILE" -o "$OUTPUT_FILE"
