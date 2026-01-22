import com.eterocell.gradle.dsl.configureAndroidApplication
import com.eterocell.gradle.dsl.configureAppSigningConfigsForRelease

plugins {
    id("com.android.application")
    id("build-logic.android.base")
}

configureAndroidApplication {
    defaultConfig {
        applicationId =
            findProperty("aneon.project.group")?.toString()
                ?: error("Property 'aneon.project.group' not found in gradle.properties")
        targetSdk = 36

        versionCode =
            findProperty("aneon.project.version.code")?.toString()?.toInt()
                ?: error("Property 'aneon.project.version.code' not found in gradle.properties")
        versionName =
            findProperty("aneon.project.version.name")?.toString()
                ?: error("Property 'aneon.project.version.name' not found in gradle.properties")
    }
}

configureAppSigningConfigsForRelease()
