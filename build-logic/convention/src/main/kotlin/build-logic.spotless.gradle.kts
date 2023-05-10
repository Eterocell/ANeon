plugins {
    id("build-logic.root-project.base")
    id("com.diffplug.spotless")
}

val ktlintVersion = "0.48.1"

allprojects {
    configureSpotless {
        intelliJIDEARunConfiguration()

        kotlin(
            editorConfig = mapOf(
                "disabled_rules" to listOf(
                    "argument-list-wrapping",
                    "filename",
                ).joinToString(","),
                "ij_kotlin_allow_trailing_comma" to "true",
                "ij_kotlin_allow_trailing_comma_on_call_site" to "true",
            ),
            licenseHeaderFile = rootProject.file("spotless/copyright.kt").takeIf(File::exists),
            licenseHeaderConfig = {
                updateYearWithLatest(true)
                yearSeparator("-")
            },
            ktLintVersion = ktlintVersion
        )
        kotlinGradle(ktLintVersion = ktlintVersion)
    }
}
