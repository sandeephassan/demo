cmake_minimum_required(VERSION 3.15)
project(LEDFirmware C ASM)

enable_language(ASM)

set(CMAKE_C_STANDARD 11)

include_directories(${CMAKE_SOURCE_DIR}/include)

add_executable(firmware.elf
    src/main.c
    src/led.c
    startup.s
)
set_target_properties(led_test PROPERTIES EXCLUDE_FROM_ALL TRUE)

target_include_directories(led_test PRIVATE ${CMAKE_SOURCE_DIR}/include)

set_target_properties(led_test PROPERTIES
    C_COMPILER ${CMAKE_C_COMPILER}
)

target_compile_options(led_test PRIVATE -std=c99 -Wall -Wextra)

target_link_options(led_test PRIVATE)

set(CMAKE_RUNTIME_OUTPUT_DIRECTORY ${CMAKE_BINARY_DIR})

add_executable(firmware.elf
    src/main.c
    src/led.c
)

target_compile_options(firmware.elf PRIVATE
    -mcpu=cortex-m4
    -mthumb
    -ffreestanding
)

target_link_options(firmware.elf PRIVATE
    -mcpu=cortex-m4
    -mthumb
    -T${CMAKE_SOURCE_DIR}/linker.ld
    -nostdlib
    -nostartfiles
    -Wl,--gc-sections
)

find_program(OBJCOPY NAMES arm-none-eabi-objcopy REQUIRED)

add_custom_command(
    TARGET firmware.elf
    POST_BUILD
    COMMAND ${OBJCOPY} -O ihex $<TARGET_FILE:firmware.elf> ${CMAKE_RUNTIME_OUTPUT_DIRECTORY}/firmware.hex
    COMMAND ${OBJCOPY} -O binary $<TARGET_FILE:firmware.elf> ${CMAKE_RUNTIME_OUTPUT_DIRECTORY}/firmware.bin
    COMMENT "Generating HEX and BIN"
)
