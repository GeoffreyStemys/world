plugins {
    id("spring-boot-app")
}

dependencies {
    implementation(libs.kotlinLogging)

    // MQTT
    implementation(libs.eclipseKuraCloud)
    implementation(libs.eclipseKuraApi)
    implementation(libs.eclipseTaho)
    implementation(libs.eclipseKuraCore)

    implementation("org.fusesource.mqtt-client:mqtt-client:1.16")
}
