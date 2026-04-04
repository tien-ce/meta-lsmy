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
    dialog --yesno "A new version software is available: \n\n$UPGRADES\n\n Do you want to update??" 10 50

    if [ $? -eq 0 ]; then
        opkg upgrade
    fi
fi

exit 0