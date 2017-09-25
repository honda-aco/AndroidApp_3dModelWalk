# https://developer.android.com/studio/projects/add-native-code.html#link-gradle
#
# Sets the minimum version of CMake required to build your native library.
# This ensures that a certain set of CMake features is available to
# your build.

if (CMAKE_VERSION VERSION_LESS "3.1")
    if (CMAKE_CXX_COMPILER_ID STREQUAL "GNU")
        set (CMAKE_CXX_FLAGS "-std=gnu++11 ${CMAKE_CXX_FLAGS}")
    endif ()
else ()
    set (CMAKE_CXX_STANDARD 11)
endif ()

cmake_minimum_required(VERSION 3.4.1)

# Specifies a library name, specifies whether the library is STATIC or
# SHARED, and provides relative paths to the source code. You can
# define multiple libraries by adding multiple add.library() commands,
# and CMake builds them for you. When you build your app, Gradle
# automatically packages shared libraries with your APK.

## 自作のソースファイルを指定
##add_library( # Specifies the name of the library.
#             NativeOpenGL
#
#             # Sets the library as a shared library.
#             SHARED
#
#             # Provides a relative path to your source file(s).
#             src/main/cpp/Bitmap.cpp
#             src/main/cpp/Common.cpp
#             src/main/cpp/ConvertObjFile.cpp
#             src/main/cpp/OperateOpenGL.cpp
#             src/main/cpp/UtilityString.cpp )
#
## Specifies a path to native header files.
## Headerファイルを指定
#include_directories( src/main/cpp
#                     ${ANDROID_NDK}/sources/android/native_app_glue )
#
## NDK内のライブラリを指定
#find_library( # Defines the name of the path variable that stores the
#              # location of the NDK library.
#              log-lib
#
#              # Specifies the name of the NDK library that
#              # CMake needs to locate.
#              log )
#
#find_library( android-lib
#              android )
#
#find_library( EGL-lib
#              EGL )
#
#find_library( GLESv1_CM-lib
#              GLESv1_CM )
#
#find_library( GLESv2-lib
#              GLESv2 )
#
## コンパイルを要するNDK内のファイルを指定
#add_library( app-glue
#             STATIC
#             ${ANDROID_NDK}/sources/android/native_app_glue/android_native_app_glue.c )
#
## Links your native library against one or more other native libraries.
## 指定のライブラリのリンク
#target_link_libraries( # Specifies the target library.
#                       NativeOpenGL
#
#                       # Links the log library to the target library.
#                       app-glue ${log-lib} ${android-lib} ${EGL-lib} ${GLESv1_CM-lib} ${GLESv2-lib})

# NDKとは別にインポートするライブラリを指定
add_library( NativeOpenGL
             SHARED
             IMPORTED )

# importしたライブラリをリンク
set_target_properties( # Specifies the target library.
                       NativeOpenGL

                       # Specifies the parameter you want to define.
                       PROPERTIES IMPORTED_LOCATION

                       # Provides the path to the library you want to import.
                       # src/main/lib
                       .externalNativeBuild/cmake/debug/obj/${ANDROID_ABI}/libNativeOpenGL.so)