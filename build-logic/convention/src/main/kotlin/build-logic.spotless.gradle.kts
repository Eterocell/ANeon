/*
 * Copyright (c) 2023 Eterocell
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
plugins {
    id("build-logic.root-project.base")
    id("com.diffplug.spotless")
}

val ktlintVersion = "0.47.1"

allprojects {
    configureSpotless {
        copyrightForKts(
            excludeTargets = setOf("**/build/**/*.kts", "**/spotless/copyright.kts"),
            licenseHeaderFile = rootProject.file("spotless/copyright.kt").takeIf(File::exists),
            licenseHeaderConfig = {
                updateYearWithLatest(true)
                yearSeparator("-")
            },
        )

        copyrightForXml(
            excludeTargets = setOf("**/build/**/*.xml", "**/spotless/copyright.xml"),
            licenseHeaderFile = rootProject.file("spotless/copyright.xml").takeIf(File::exists),
            licenseHeaderConfig = {
                updateYearWithLatest(true)
                yearSeparator("-")
            },
        )

        androidXml()
        intelliJIDEARunConfiguration()
        gradleVersionCatalogs()

        kotlin(
            editorConfig = mapOf(
                "ktlint_disabled_rules" to listOf(
                    "argument-list-wrapping",
                    "filename",
                    "no-wildcard-imports",
                ).joinToString(","),
                "ij_kotlin_allow_trailing_comma" to "true",
                "ij_kotlin_allow_trailing_comma_on_call_site" to "true",
                "ktlint_standard_argument-list-wrapping" to "disabled",
                "ktlint_standard_filename" to "disabled",
            ),
            licenseHeaderFile = rootProject.file("spotless/copyright.kt").takeIf(File::exists),
            excludeTargets = listOf("**/spotless/copyright.kt", "*.kts"),
            licenseHeaderConfig = {
                updateYearWithLatest(true)
                yearSeparator("-")
            },
            ktLintVersion = ktlintVersion,
        )
        kotlinGradle(ktLintVersion = ktlintVersion)
    }
}
