import com.diffplug.gradle.spotless.FormatExtension
import com.diffplug.gradle.spotless.SpotlessExtension
import com.diffplug.gradle.spotless.SpotlessPlugin
import com.diffplug.spotless.kotlin.KtLintStep
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import java.io.File

fun Project.configureSpotless(block: SpotlessExtension.() -> Unit) {
    apply<SpotlessPlugin>()
    plugins.withId("com.diffplug.spotless") { configure(block) }
}

fun SpotlessExtension.androidXml(
    block: FormatExtension.() -> Unit = {
        target(
            "**/AndroidManifest.xml",
            "src/**/*.xml",
        )
        indentWithSpaces()
        trimTrailingWhitespace()
        endWithNewline()
    },
) = format("androidXml", block)

fun SpotlessExtension.gradleVersionCatalogs(
    block: FormatExtension.() -> Unit = {
        target(
            "**/*.versions.toml",
        )
        indentWithSpaces()
        trimTrailingWhitespace()
        endWithNewline()
    },
) = format("gradleVersionCatalogs", block)

fun SpotlessExtension.intelliJIDEARunConfiguration(
    block: FormatExtension.() -> Unit = {
        target(
            "**/.run/*.xml",
            "**/.idea/runConfigurations/*.xml",
        )
        indentWithSpaces()
        trimTrailingWhitespace()
        endWithNewline()
    },
) = format("intelliJIDEARunConfiguration", block)

fun SpotlessExtension.kotlin(
    targets: List<String> = listOf("src/**/*.kt"),
    excludeTargets: List<String> = listOf(),
    ktLintVersion: String = KtLintStep.defaultVersion(),
    licenseHeaderFile: File? = null,
    licenseHeaderConfig: FormatExtension.LicenseHeaderConfig.() -> Unit = {},
    userData: Map<String, String> = mapOf("android" to "true"),
    editorConfigPath: String,
    editorConfigOverride: Map<String, String> = mapOf(),
) = kotlin {
    target(targets)
    targetExclude(excludeTargets)
    indentWithSpaces()
    trimTrailingWhitespace()
    endWithNewline()
    ktlint(ktLintVersion).userData(userData)
        .setEditorConfigPath(editorConfigPath.takeIf { File(it).exists() })
        .editorConfigOverride(editorConfigOverride)
    licenseHeaderFile?.let(::licenseHeaderFile)?.apply(licenseHeaderConfig)
}

private val defaultExcludeTargetsForKotlinGradle: Set<String> = setOf(
    "**/build/kotlin-dsl/**/*.gradle.kts",
)

fun SpotlessExtension.kotlinGradle(
    targets: List<String> = listOf("**/*.gradle.kts"),
    overrideExcludeTargets: Set<String> = setOf(),
    additionalExcludeTargets: Set<String> = setOf(),
    ktLintVersion: String = KtLintStep.defaultVersion(),
    userData: Map<String, String> = mapOf(),
    editorConfigPath: String,
    editorConfigOverride: Map<String, String> = mapOf(),
) = kotlinGradle {
    target(targets)
    overrideExcludeTargets.ifEmpty { defaultExcludeTargetsForKotlinGradle + additionalExcludeTargets }
        .let { targetExclude(it) }
    indentWithSpaces()
    trimTrailingWhitespace()
    endWithNewline()
    ktlint(ktLintVersion).userData(userData)
        .setEditorConfigPath(editorConfigPath.takeIf { File(it).exists() })
        .editorConfigOverride(editorConfigOverride)
}

fun SpotlessExtension.protobuf(
    clangFormatVersion: String = "13.0.0",
    style: String = "Google",
    block: FormatExtension.() -> Unit = {
        target("src/**/*.proto")
        clangFormat(clangFormatVersion).style(style)
    },
) = format("protobuf", block)

fun SpotlessExtension.copyrightForKts(
    targets: List<String> = listOf("**/*.kts"),
    excludeTargets: Set<String> = setOf(),
    licenseHeaderFile: File? = null,
    licenseHeaderDelimiter: String = "(^(?![\\/ ]\\*).*\$)",
    licenseHeaderConfig: FormatExtension.LicenseHeaderConfig.() -> Unit = {},
) {
    format("kts") {
        target(targets)
        targetExclude(excludeTargets)
        licenseHeaderFile?.let { licenseHeaderFile(it, licenseHeaderDelimiter) }
            ?.apply(licenseHeaderConfig)
    }
}

fun SpotlessExtension.copyrightForXml(
    targets: List<String> = listOf("**/*.xml"),
    excludeTargets: Set<String> = setOf(),
    licenseHeaderFile: File? = null,
    licenseHeaderDelimiter: String = "(<[^!?])",
    licenseHeaderConfig: FormatExtension.LicenseHeaderConfig.() -> Unit = {},
) {
    format("xml") {
        target(targets)
        targetExclude(excludeTargets)
        licenseHeaderFile?.let { licenseHeaderFile(it, licenseHeaderDelimiter) }
            ?.apply(licenseHeaderConfig)
    }
}
