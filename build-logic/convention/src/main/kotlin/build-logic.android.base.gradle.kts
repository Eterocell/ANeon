import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import com.eterocell.gradle.dsl.configureAndroidCommon
import com.eterocell.gradle.dsl.libs
import com.eterocell.gradle.dsl.withAndroidApplication
import com.eterocell.gradle.dsl.withAndroidLibrary
import org.gradle.kotlin.dsl.configure

plugins {
    id("com.android.base")
    id("build-logic.kotlin.android")
}

configureAndroidCommon {
    namespace = project.androidNamespace
    compileSdk {
        version = release(36)
    }
    buildToolsVersion = "36.1.0"
    defaultConfig.apply {
        minSdk = 24
    }
    compileOptions.apply {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
        isCoreLibraryDesugaringEnabled = true
    }
}

dependencies {
    add("coreLibraryDesugaring", libs.findLibrary("android-desugar-jdk-libs").get())
}

val Project.androidNamespace: String
    get() {
        val group =
            findProperty("aneon.project.group") as? String
                ?: error("Property 'aneon.project.group' not found in gradle.properties")
        val suffix =
            path
                .replace(":", ".")
                .let { if (it == ".app") "" else it.replace("-", ".") }
        return group + suffix
    }
