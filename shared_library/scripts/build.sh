#!/bin/sh
set -e
PROJECT_DIR="$1"
BUILD_DIR="$2"
mkdir -p "$PROJECT_DIR/$BUILD_DIR"
cd "$PROJECT_DIR/$BUILD_DIR"
cmake .. -DCMAKE_TOOLCHAIN_FILE=../arm-gcc-toolchain.cmake
cmake --build .
