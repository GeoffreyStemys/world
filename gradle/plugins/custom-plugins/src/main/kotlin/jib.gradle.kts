import Jib_gradle.Registry.DOCKER_HUB_MULTI_ARCH
import com.google.cloud.tools.jib.gradle.BuildDockerTask
import com.google.cloud.tools.jib.gradle.BuildImageTask
import com.google.cloud.tools.jib.gradle.JibExtension
import com.google.cloud.tools.jib.gradle.PlatformParameters

plugins {
  id("docker-compose")
  id("com.google.cloud.tools.jib")
}

val dockerhubUsername: String by project
val dockerhubPassword: String by project

enum class Registry(val taskName: String, val registryName: String) {
  DOCKER_HUB_MULTI_ARCH("jibPushDockerHub", "stemysio"),
  DOCKER_HUB_LOCAL("jibBuildLocal", "stemysio");
}

Registry.values().forEach { registry ->
  when (registry) {
    DOCKER_HUB_MULTI_ARCH -> {
      tasks.register<BuildImageTask>(registry.taskName) {
        val jibExtension = createJib(registry.registryName)
        jibExtension.from.platforms = listOf(platformParameters("amd64"), platformParameters("arm64"))
        jibExtension.to.auth.username = dockerhubUsername
        jibExtension.to.auth.password = dockerhubPassword
        jibExtension.container.user = "1000"
        setJibExtension(jibExtension)
      }
      dependsOn(registry.taskName)
    }

    Registry.DOCKER_HUB_LOCAL -> {
      tasks.register<BuildDockerTask>(registry.taskName) {
        val jibExtension = createJib(registry.registryName)
        val archi = when (System.getProperty("os.arch")) {
          "aarch64" -> "arm64"
          "amd64" -> "amd64"
          "x86_64" -> "amd64"
          else -> throw RuntimeException("Unsupported architecture: ${System.getProperty("os.arch")}")
        }
        jibExtension.from.platforms = listOf(platformParameters(archi))
        jibExtension.to.auth.username = dockerhubUsername
        jibExtension.to.auth.password = dockerhubPassword
        setJibExtension(jibExtension)
      }
      dependsOn(registry.taskName)
    }
  }
}

fun createJib(registry: String): JibExtension {
  val jibExtension = JibExtension(project)
  jibExtension.from.image = "amazoncorretto:21"
  val extraTag = when (project.version.toString().lowercase().endsWith("snapshot")) {
    true -> "unstable"
    false -> "latest"
  }
  jibExtension.to.image = "$registry/${project.name}:${project.version}"
  jibExtension.to.tags = setOf(extraTag)
  jibExtension.container.ports = listOf("8080")
  jibExtension.container.creationTime.set("USE_CURRENT_TIMESTAMP")
  return jibExtension
}

fun platformParameters(archi: String) = PlatformParameters().apply {
  os = "linux"
  architecture = archi
}

fun dependsOn(taskName: String) {
  tasks.named(taskName) {
    dependsOn("build")
    mustRunAfter("build")
  }
}
