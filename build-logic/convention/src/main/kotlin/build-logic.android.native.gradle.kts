import com.eterocell.gradle.dsl.configureAndroidCommon

configureAndroidCommon {
    ndkVersion = "26.2.11394342"

    defaultConfig.externalNativeBuild {
        cmake {
            arguments += "-DANDROID_STL=c++_shared"
            cFlags += "-std=c18"
            cppFlags += "-std=c++20"
            abiFilters("arm64-v8a", "armeabi-v7a", "x86", "x86_64")
        }
    }
    externalNativeBuild {
        cmake {
            version = "3.22.1"
        }
    }
}
