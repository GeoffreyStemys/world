plugins {
  `kotlin-dsl` // https://plugins.gradle.org/plugin/org.gradle.kotlin.kotlin-dsl
}

val kotlinVersion: String by project

val springBootVersion: String by project
val springDependencyManagementVersion: String by project
val jibVersion: String by project
val jacksonVersion: String by project
val dockerComposeVersion: String by project
val semverVersion: String by project

dependencies {
  implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
  implementation("org.jetbrains.kotlin:kotlin-allopen:$kotlinVersion")
  implementation("org.jetbrains.kotlin.plugin.noarg:org.jetbrains.kotlin.plugin.noarg.gradle.plugin:$kotlinVersion")
  implementation("org.jetbrains.kotlin.plugin.jpa:org.jetbrains.kotlin.plugin.jpa.gradle.plugin:$kotlinVersion")

  implementation("org.springframework.boot:spring-boot-gradle-plugin:$springBootVersion")
  implementation("io.spring.gradle:dependency-management-plugin:$springDependencyManagementVersion")

  implementation("com.google.cloud.tools:jib-gradle-plugin:$jibVersion")
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")

  implementation("com.javiersc.semver:semver-gradle-plugin:$semverVersion")
  implementation("com.avast.gradle:gradle-docker-compose-plugin:$dockerComposeVersion")
}
