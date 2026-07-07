#!/usr/bin/env groovy

/**
 * runClangFormat(projectDir, buildDir)
 * Runs clang-format checks and writes a report into ${projectDir}/${buildDir}/clang-format-report.txt
 */
def call(String projectDir = '.', String buildDir = 'build') {
  def reportDir = "${projectDir}/${buildDir}"
  sh "mkdir -p ${reportDir}"
  def report = "${reportDir}/clang-format-report.txt"
  sh "echo 'Clang-format report generated at: ${report}' > ${report}"

  if (sh(script: 'command -v clang-format >/dev/null 2>&1 || echo no', returnStdout: true).trim() != 'no') {
    sh "echo 'Checking C files...' >> ${report}"
    sh "find ${projectDir}/src ${projectDir}/include -type f -name \"*.c\" -print0 | xargs -0 --no-run-if-empty sh -c 'clang-format --dry-run --Werror \"\$@\" 2>&1' sh >> ${report} || true"
    sh "echo 'Checking H files...' >> ${report}"
    sh "find ${projectDir}/src ${projectDir}/include -type f -name \"*.h\" -print0 | xargs -0 --no-run-if-empty sh -c 'clang-format --dry-run --Werror \"\$@\" 2>&1' sh >> ${report} || true"
  } else {
    sh "echo 'clang-format not found; skipping lint' >> ${report}"
  }

  // Print small summary
  sh "echo '--- clang-format summary ---'"
  sh "tail -n +1 ${report} || true"
}
