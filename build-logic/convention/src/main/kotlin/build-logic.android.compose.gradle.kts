import com.eterocell.gradle.dsl.configureAndroidCommon

configureAndroidCommon {
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.7"
    }
}
