#!/usr/bin/env groovy

/**
 * buildFirmware(projectDir, buildDir)
 * Runs CMake configure and build in the specified project/build directory
 */
def call(String projectDir = '.', String buildDir = 'build') {
  sh "mkdir -p ${projectDir}/${buildDir}"
  dir("${projectDir}/${buildDir}") {
    sh "cmake .. -DCMAKE_TOOLCHAIN_FILE=../arm-gcc-toolchain.cmake"
    sh "cmake --build ."
  }
}
