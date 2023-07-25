import com.eterocell.gradle.dsl.kotlinCompile

plugins {
    kotlin("android")
}

kotlinCompile {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()

        val warningsAsErrors: String? by project
        allWarningsAsErrors = warningsAsErrors.toBoolean()
        freeCompilerArgs = freeCompilerArgs + listOf(
            "-opt-in=kotlin.RequiresOptIn",
            "-opt-in=kotlinx.coroutines.ExperimentalCoroutineApi",
            "-opt-in=kotlinx.coroutines.FlowPreview",
        )
    }
}
