#!/bin/sh
set -e
echo "led-shared: install packages (script)"
apt-get update || true
DEBIAN_FRONTEND=noninteractive apt-get install -y --no-install-recommends cmake clang-format gcc-arm-none-eabi binutils-arm-none-eabi build-essential || true
