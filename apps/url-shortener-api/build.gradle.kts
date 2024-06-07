dependencies {
    implementation(project(":infrastructure:mysql"))
    implementation(project(":domain:url-shortener"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
}
