# CMAKE generated file: DO NOT EDIT!
# Generated by "MinGW Makefiles" Generator, CMake Version 3.22

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:

#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:

# Disable VCS-based implicit rules.
% : %,v

# Disable VCS-based implicit rules.
% : RCS/%

# Disable VCS-based implicit rules.
% : RCS/%,v

# Disable VCS-based implicit rules.
% : SCCS/s.%

# Disable VCS-based implicit rules.
% : s.%

.SUFFIXES: .hpux_make_needs_suffix_list

# Command-line flag to silence nested $(MAKE).
$(VERBOSE)MAKESILENT = -s

#Suppress display of executed commands.
$(VERBOSE).SILENT:

# A target that is always out of date.
cmake_force:
.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

SHELL = cmd.exe

# The CMake executable.
CMAKE_COMMAND = "D:\jetbrans_tools\CLion 2022.1.3\bin\cmake\win\bin\cmake.exe"

# The command to remove a file.
RM = "D:\jetbrans_tools\CLion 2022.1.3\bin\cmake\win\bin\cmake.exe" -E rm -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = C:\Users\qkdlf\Study\OperatingSystem\operatingsystem_c

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = C:\Users\qkdlf\Study\OperatingSystem\operatingsystem_c\cmake-build-debug

# Include any dependencies generated for this target.
include CMakeFiles/bounded_buffer_test.dir/depend.make
# Include any dependencies generated by the compiler for this target.
include CMakeFiles/bounded_buffer_test.dir/compiler_depend.make

# Include the progress variables for this target.
include CMakeFiles/bounded_buffer_test.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/bounded_buffer_test.dir/flags.make

CMakeFiles/bounded_buffer_test.dir/chap07_synchronization/7.1_bounded_buffer.c.obj: CMakeFiles/bounded_buffer_test.dir/flags.make
CMakeFiles/bounded_buffer_test.dir/chap07_synchronization/7.1_bounded_buffer.c.obj: ../chap07_synchronization/7.1_bounded_buffer.c
CMakeFiles/bounded_buffer_test.dir/chap07_synchronization/7.1_bounded_buffer.c.obj: CMakeFiles/bounded_buffer_test.dir/compiler_depend.ts
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=C:\Users\qkdlf\Study\OperatingSystem\operatingsystem_c\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building C object CMakeFiles/bounded_buffer_test.dir/chap07_synchronization/7.1_bounded_buffer.c.obj"
	"D:\jetbrans_tools\CLion 2022.1.3\bin\mingw\bin\gcc.exe" $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -MD -MT CMakeFiles/bounded_buffer_test.dir/chap07_synchronization/7.1_bounded_buffer.c.obj -MF CMakeFiles\bounded_buffer_test.dir\chap07_synchronization\7.1_bounded_buffer.c.obj.d -o CMakeFiles\bounded_buffer_test.dir\chap07_synchronization\7.1_bounded_buffer.c.obj -c C:\Users\qkdlf\Study\OperatingSystem\operatingsystem_c\chap07_synchronization\7.1_bounded_buffer.c

CMakeFiles/bounded_buffer_test.dir/chap07_synchronization/7.1_bounded_buffer.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/bounded_buffer_test.dir/chap07_synchronization/7.1_bounded_buffer.c.i"
	"D:\jetbrans_tools\CLion 2022.1.3\bin\mingw\bin\gcc.exe" $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E C:\Users\qkdlf\Study\OperatingSystem\operatingsystem_c\chap07_synchronization\7.1_bounded_buffer.c > CMakeFiles\bounded_buffer_test.dir\chap07_synchronization\7.1_bounded_buffer.c.i

CMakeFiles/bounded_buffer_test.dir/chap07_synchronization/7.1_bounded_buffer.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/bounded_buffer_test.dir/chap07_synchronization/7.1_bounded_buffer.c.s"
	"D:\jetbrans_tools\CLion 2022.1.3\bin\mingw\bin\gcc.exe" $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S C:\Users\qkdlf\Study\OperatingSystem\operatingsystem_c\chap07_synchronization\7.1_bounded_buffer.c -o CMakeFiles\bounded_buffer_test.dir\chap07_synchronization\7.1_bounded_buffer.c.s

# Object files for target bounded_buffer_test
bounded_buffer_test_OBJECTS = \
"CMakeFiles/bounded_buffer_test.dir/chap07_synchronization/7.1_bounded_buffer.c.obj"

# External object files for target bounded_buffer_test
bounded_buffer_test_EXTERNAL_OBJECTS =

bounded_buffer_test.exe: CMakeFiles/bounded_buffer_test.dir/chap07_synchronization/7.1_bounded_buffer.c.obj
bounded_buffer_test.exe: CMakeFiles/bounded_buffer_test.dir/build.make
bounded_buffer_test.exe: CMakeFiles/bounded_buffer_test.dir/linklibs.rsp
bounded_buffer_test.exe: CMakeFiles/bounded_buffer_test.dir/objects1.rsp
bounded_buffer_test.exe: CMakeFiles/bounded_buffer_test.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=C:\Users\qkdlf\Study\OperatingSystem\operatingsystem_c\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking C executable bounded_buffer_test.exe"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles\bounded_buffer_test.dir\link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/bounded_buffer_test.dir/build: bounded_buffer_test.exe
.PHONY : CMakeFiles/bounded_buffer_test.dir/build

CMakeFiles/bounded_buffer_test.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles\bounded_buffer_test.dir\cmake_clean.cmake
.PHONY : CMakeFiles/bounded_buffer_test.dir/clean

CMakeFiles/bounded_buffer_test.dir/depend:
	$(CMAKE_COMMAND) -E cmake_depends "MinGW Makefiles" C:\Users\qkdlf\Study\OperatingSystem\operatingsystem_c C:\Users\qkdlf\Study\OperatingSystem\operatingsystem_c C:\Users\qkdlf\Study\OperatingSystem\operatingsystem_c\cmake-build-debug C:\Users\qkdlf\Study\OperatingSystem\operatingsystem_c\cmake-build-debug C:\Users\qkdlf\Study\OperatingSystem\operatingsystem_c\cmake-build-debug\CMakeFiles\bounded_buffer_test.dir\DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/bounded_buffer_test.dir/depend

