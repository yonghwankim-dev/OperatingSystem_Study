cmake_minimum_required(VERSION 3.22)
project(operatingsystem_c C)

set(CMAKE_C_STANDARD 99)

add_executable(bounded_buffer_test chap07_synchronization/7.1_bounded_buffer.c)
add_executable(philospher_test chap07_synchronization/7.2_philosopher.c)
