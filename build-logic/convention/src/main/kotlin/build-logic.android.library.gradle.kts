import com.eterocell.gradle.dsl.configureAndroidLibrary

plugins {
    id("com.android.library")
    id("build-logic.android.base")
}

configureAndroidLibrary {
    defaultConfig {
        targetSdk = 34
    }
    buildFeatures {
        buildConfig = false
    }
}
