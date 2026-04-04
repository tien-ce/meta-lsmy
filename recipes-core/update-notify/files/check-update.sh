#!/bin/sh

if ! opkg update; then
    echo "Opkg update failed"
    exit 1
fi

if ! UPGRADES="$(opkg list-upgradable)"; then
    echo "Opkg list-upgradable failed"
    exit 1
fi

if [ -n "$UPGRADES" ]; then
    echo "A new version software is available, do you want to update?"
    echo "$UPGRADES"
fi

exit 0