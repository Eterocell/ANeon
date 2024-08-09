plugins {
    id("build-logic.root-project.base")
    id("build-logic.spotless")
}

val wrapper: Wrapper by tasks.named<Wrapper>("wrapper") {
    gradleVersion = "8.9"
    distributionType = Wrapper.DistributionType.ALL
}

tasks.clean {
    allprojects.forEach {
        delete(it.layout.buildDirectory)
        delete(files("${it.layout.projectDirectory.asFile.absolutePath}/.cxx"))
    }
}
