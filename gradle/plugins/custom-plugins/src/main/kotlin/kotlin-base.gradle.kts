plugins {
  id("org.jetbrains.kotlin.jvm")
}

group = "ch.brw.hive"

tasks.withType<Test> {
  useJUnitPlatform()
//    testLogging.showStandardStreams = true
//    testLogging.exceptionFormat = FULL
}

dependencies {
  implementation("org.jetbrains.kotlin:kotlin-reflect")
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
}
