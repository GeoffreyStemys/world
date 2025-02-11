plugins {
  id("kotlin-base")
  `maven-publish`

  id("com.javiersc.semver")
  id("project-report")
  jacoco
}

val nexusUrl: String by project
val stemysNexusLegacy = "$nexusUrl/repository/stemys-nexus-legacy/"
val nexusRepoReleases = "$nexusUrl/repository/maven-releases/"
val nexusRepoSnapshots = "$nexusUrl/repository/maven-snapshots/"
val mavenCentral = "$nexusUrl/repository/maven-central/"
val nexusUsername: String by project
val nexusPassword: String by project

repositories {
  mavenLocal()
  maven {
    url = uri(stemysNexusLegacy)
    credentials {
      username = nexusUsername
      password = nexusPassword
    }
  }
  maven {
    url = uri(nexusRepoReleases)
    credentials {
      username = nexusUsername
      password = nexusPassword
    }
  }
  maven {
    url = uri(nexusRepoSnapshots)
    credentials {
      username = nexusUsername
      password = nexusPassword
    }
  }
  maven {
    url = uri("https://repo.akka.io/maven/")
  }
  maven {
    url = uri(mavenCentral)
    credentials {
      username = nexusUsername
      password = nexusPassword
    }
  }
  maven {
    url = uri("https://repo.eclipse.org/content/groups/releases/")
  }
}


publishing {
  repositories {
    maven {
      val theVersion = project.version.toString()
      val nexusRepoUrl = when (theVersion.lowercase().endsWith("snapshot")) {
        true -> nexusRepoSnapshots
        false -> nexusRepoReleases
      }
      url = uri(nexusRepoUrl)
      credentials {
        username = nexusUsername
        password = nexusPassword
      }
    }
  }
}

tasks.jacocoTestReport {
  dependsOn(tasks.test) // tests are required to run before generating the report
}

tasks.jacocoTestReport {
  reports {
    xml.required.set(true)
    html.required.set(true)
  }
}
