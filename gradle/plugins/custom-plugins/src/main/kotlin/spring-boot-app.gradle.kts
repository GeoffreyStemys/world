plugins {
  id("jib")
  kotlin("plugin.spring") // id("org.jetbrains.kotlin.plugin.spring")

  id("org.springframework.boot")
  id("io.spring.dependency-management")
  id("org.jetbrains.kotlin.plugin.jpa")
  id("org.jetbrains.kotlin.plugin.noarg")
}

val springCloudVersion: String by project
val otelBomVersion: String by project

springBoot {
  buildInfo()
}

dependencyManagement {
  imports {
    mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
    mavenBom("io.opentelemetry.instrumentation:opentelemetry-instrumentation-bom:$otelBomVersion")
  }
}

dependencies {
  implementation("org.springframework.boot:spring-boot-starter")
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("org.springframework.boot:spring-boot-starter-validation")
  implementation("org.springframework.boot:spring-boot-starter-actuator")
  runtimeOnly("io.micrometer:micrometer-registry-prometheus")
  implementation("org.springframework.boot:spring-boot-starter-security")
  implementation("org.springframework.cloud:spring-cloud-starter-kubernetes-client-config")

  implementation("org.springframework:spring-jms:6.2.1")
  implementation("com.rabbitmq.jms:rabbitmq-jms:3.4.0")

  implementation("io.opentelemetry.instrumentation:opentelemetry-spring-boot-starter")

  annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

  testImplementation("org.springframework.boot:spring-boot-starter-test") {
    exclude(module = "mockito-core")
  }

}
