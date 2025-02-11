plugins {
    id("deploy-app")
    id("com.avast.gradle.docker-compose")
}

val withDockerCompose: String by project

dockerCompose {
    useComposeFiles.set(listOf("$projectDir/src/test/docker/docker-compose.yaml"))
    when (withDockerCompose) {
        "true" -> isRequiredBy(tasks.getByName("test"))
        else -> println("skipping docker-compose")
    }
}
