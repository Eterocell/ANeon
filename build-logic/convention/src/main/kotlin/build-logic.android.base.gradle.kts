import com.eterocell.gradle.dsl.configureAndroidCommon
import com.eterocell.gradle.dsl.libs

plugins {
    id("com.android.base")
    id("build-logic.kotlin.android")
}

configureAndroidCommon {
    namespace = androidNamespace
    compileSdk = 36
    buildToolsVersion = "36.1.0"
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
        isCoreLibraryDesugaringEnabled = true
    }
}

dependencies {
    add("coreLibraryDesugaring", libs.findLibrary("android-desugar-jdk-libs").get())
}

val Project.androidNamespace
    get() =
        path
            .replace(":", ".")
            .let { if (it == ".app") "" else it.replace("-", ".") }
            .let { extra["aneon.project.group"] as String + it }
