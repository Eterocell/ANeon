import com.eterocell.gradle.dsl.configureAndroidCommon

configureAndroidCommon {
    ndkVersion = "27.1.12297006"

    defaultConfig.externalNativeBuild {
        cmake {
            arguments += listOf(
                "-DANDROID_STL=c++_shared",
                "-DANDROID_SUPPORT_FLEXIBLE_PAGE_SIZES=ON", // Support 16KB memory page size devices
            )
            cFlags += "-std=c18"
            cppFlags += "-std=c++20"
            abiFilters("arm64-v8a", "armeabi-v7a", "x86", "x86_64")
        }
    }
    externalNativeBuild {
        cmake {
            version = "3.30.3"
        }
    }
}
