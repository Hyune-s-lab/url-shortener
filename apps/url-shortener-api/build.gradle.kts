dependencies {
    implementation(project(":infrastructure:mysql"))
    implementation(project(":infrastructure:redis"))
    implementation(project(":domain:url-shortener"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    val testcontainersVersion: String by project
    implementation("org.testcontainers:testcontainers:${testcontainersVersion}")
    implementation("org.testcontainers:junit-jupiter:${testcontainersVersion}")
    implementation("org.testcontainers:mysql:${testcontainersVersion}")
}
