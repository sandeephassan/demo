#!/usr/bin/env groovy

def call(String projectDir = '.') {
  sh """
  set -e
  echo "led-shared: preparing environment for ${projectDir}"
  if command -v sudo >/dev/null 2>&1; then
    sudo apt-get update
    sudo DEBIAN_FRONTEND=noninteractive apt-get install -y --no-install-recommends cmake clang-format gcc-arm-none-eabi binutils-arm-none-eabi build-essential || true
  else
    apt-get update || true
    DEBIAN_FRONTEND=noninteractive apt-get install -y --no-install-recommends cmake clang-format gcc-arm-none-eabi binutils-arm-none-eabi build-essential || true
  fi
  """
}
