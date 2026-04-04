#!/bin/sh

LOGTAG="lsmy-update"

if ! opkg update; then
    logger -t "$LOGTAG" "Opkg update failed"
    exit 1
fi

if ! UPGRADES="$(opkg list-upgradable)"; then
    logger -t "$LOGTAG" "Opkg list-upgradable failed"
    exit 1
fi

[ -n "$UPGRADES" ] && \
logger -t "$LOGTAG" "A new version software is available, do you want to update?"

exit 0