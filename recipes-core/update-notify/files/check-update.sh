#!/bin/sh

if ! opkg update; then
    echo "Opkg update failed"
    exit 1
fi

if ! UPGRADES="$(opkg list-upgradable | head -n 10)"; then
    echo "Opkg list-upgradable failed"
    exit 1
fi

if [ -n "$UPGRADES" ]; then
    echo "==== Notify Update ===="
    echo "A new version software is available: \n\n$UPGRADES\n\n Run lsmy-update to update?"
fi

exit 0