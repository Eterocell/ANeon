import com.eterocell.gradle.dsl.configureAndroidCommon

configureAndroidCommon {
    defaultConfig.vectorDrawables { useSupportLibrary = true }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion =
            libs().findVersion("compose-compiler").get().requiredVersion
    }
}
