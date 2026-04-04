#!/bin/sh
if ! UPGRADES="$(opkg list-upgradable | head -n 10)"; then
    echo "Opkg list-upgradable failed"
    exit 1
fi

if [ -n "$UPGRADES" ]; then    
    CHOICE=$(dialog --clear \
        --title "Software Update" \
        --menu "A new version software is available: \n\n$UPGRADES\n\n Do you want to update?" 20 65 4 \
        1 "Update now" \
        2 "Remind me later" \
        3 "Don't show this again" \
        2>&1 >/dev/tty)

    case "$CHOICE" in
        1)
            opkg update
            opkg upgrade
            ;;
        2)
            echo "Remind in 10 minutes"
            ;;
        3)
            echo "Don't show this again"
            systemctl stop check-update.timer
            ;;
    esac
fi

exit 0