plugins {
    id("build-logic.root-project.base")
    id("build-logic.git.hooks")
    id("build-logic.spotless")
}

val wrapper: Wrapper by tasks.named<Wrapper>("wrapper") {
    gradleVersion = "8.1.1"
    distributionType = Wrapper.DistributionType.ALL
}
