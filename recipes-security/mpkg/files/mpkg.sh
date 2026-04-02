#!/bin/sh

SOCKET="/run/lsmy/security.sock"
TOKEN="LSMY_PAUSE_SECRET_99"

BASELINE_SCRIPT="/usr/bin/gen_baseline.sh"
WHITELIST_SCRIPT="/usr/bin/gen_whitelist.sh"

BASELINE_DB="/etc/security/baseline.db"
GOLD_BACKUP="/etc/security/gold_backup.tar.gz"

notify_agent() {
    printf "%s:%s" "$1" "$TOKEN" | nc -U "$SOCKET" > /dev/null 2>&1
}

ACTION=$1

NEEDS_UPDATE=false
case "$ACTION" in
    install|upgrade|remove|autoremove)
        NEEDS_UPDATE=true
        ;;
esac

if [ "$NEEDS_UPDATE" = false ]; then
    /usr/bin/opkg "$@"
    exit $?
fi

notify_agent "START_MAINTENANCE"
echo "[LSMY] Maintenance mode ON."

[ -f "$BASELINE_DB" ] && chattr -i "$BASELINE_DB"
# [ -f "$GOLD_BACKUP" ] && chattr -i "$GOLD_BACKUP"

/usr/bin/opkg "$@"
EXIT_CODE=$?

if [ $EXIT_CODE -eq 0 ]; then
    echo "[LSMY] Updating security databases..."
    
    if [ -f "$BASELINE_SCRIPT" ]; then
        export ROOTFS="" 
        sh "$BASELINE_SCRIPT"
    fi

    # /usr/bin/gen_gold_backup.sh
    
    echo "[LSMY] Security databases updated."
else
    echo "[LSMY] OPKG failed (Exit: $EXIT_CODE). Security databases not updated."
fi

[ -f "$BASELINE_DB" ] && chattr +i "$BASELINE_DB"
# [ -f "$GOLD_BACKUP" ] && chattr +i "$GOLD_BACKUP"

notify_agent "STOP_MAINTENANCE"
echo "[LSMY] Maintenance mode OFF."

exit $EXIT_CODE