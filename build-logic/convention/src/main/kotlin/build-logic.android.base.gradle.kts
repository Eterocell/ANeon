import com.eterocell.gradle.dsl.configureAndroidCommon

plugins {
    id("com.android.base")
    kotlin("android")
}

configureAndroidCommon {
    namespace = androidNamespace
    compileSdk = 33
    buildToolsVersion = "33.0.2"
    defaultConfig {
        minSdk = 29
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

val Project.androidNamespace
    get() = path.replace(":", ".")
        .let { if (it == ".android") "" else it.replace("-", ".") }
        .let { extra["aneon.project.group"] as String + it }
