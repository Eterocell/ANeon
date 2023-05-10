import com.eterocell.gradle.dsl.configureAndroidApplication

plugins {
    id("com.android.application")
    id("build-logic.android.base")
}

configureAndroidApplication {
    defaultConfig {
        targetSdk = 33
    }
}

configureAppSigningConfigsForRelease()
