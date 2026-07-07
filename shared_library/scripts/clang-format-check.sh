#!/bin/sh
set -e
REPORT_DIR="$1"
REPORT="$REPORT_DIR/clang-format-report.txt"
mkdir -p "$REPORT_DIR"
echo "Clang-format report generated at: $REPORT" > "$REPORT"
if command -v clang-format >/dev/null 2>&1; then
  echo "Checking C files..." >> "$REPORT"
  find src include -type f -name "*.c" -print0 | xargs -0 --no-run-if-empty sh -c 'clang-format --dry-run --Werror "$@" 2>&1' sh >> "$REPORT" || true
  echo "Checking H files..." >> "$REPORT"
  find src include -type f -name "*.h" -print0 | xargs -0 --no-run-if-empty sh -c 'clang-format --dry-run --Werror "$@" 2>&1' sh >> "$REPORT" || true
else
  echo "clang-format not found; skipping lint" >> "$REPORT"
fi
echo "--- clang-format summary ---"
tail -n +1 "$REPORT" || true
