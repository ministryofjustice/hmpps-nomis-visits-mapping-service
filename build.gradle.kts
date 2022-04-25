plugins {
  id("uk.gov.justice.hmpps.gradle-spring-boot") version "4.1.5-beta-3"
  kotlin("plugin.spring") version "1.6.21"
  kotlin("plugin.jpa") version "1.6.21"
  idea
}

dependencyCheck {
  suppressionFiles.add("reactive-suppressions.xml")
}

configurations {
  implementation { exclude(module = "spring-boot-starter-web") }
  implementation { exclude(module = "spring-boot-starter-tomcat") }
  testImplementation { exclude(group = "org.junit.vintage") }
}

dependencies {
  implementation("org.springframework.boot:spring-boot-starter-webflux")
  implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
  implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
  implementation("org.springframework.boot:spring-boot-starter-security")
  implementation("org.springframework.boot:spring-boot-starter-oauth2-client")

  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.6.1")

  implementation("org.flywaydb:flyway-core:8.5.9")
  implementation("com.vladmihalcea:hibernate-types-52:2.16.1")
  runtimeOnly("io.r2dbc:r2dbc-postgresql")
  runtimeOnly("org.springframework.boot:spring-boot-starter-jdbc")
  runtimeOnly("org.postgresql:postgresql:42.3.4")
  implementation("io.opentelemetry:opentelemetry-api:1.13.0")

  implementation("org.springdoc:springdoc-openapi-webflux-ui:1.6.8")
  implementation("org.springdoc:springdoc-openapi-kotlin:1.6.8")
  implementation("org.springdoc:springdoc-openapi-security:1.6.8")

  implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.2")

  implementation("com.zaxxer:HikariCP:5.0.1")

  developmentOnly("org.springframework.boot:spring-boot-devtools")

  testImplementation("org.awaitility:awaitility-kotlin:4.2.0")
  testImplementation("io.jsonwebtoken:jjwt:0.9.1")
  testImplementation("org.mockito:mockito-inline:4.5.1")
  testImplementation("io.swagger.parser.v3:swagger-parser:2.0.32")
  testImplementation("org.springframework.security:spring-security-test")
  testImplementation("org.testcontainers:postgresql:1.17.1")
  testImplementation("io.projectreactor:reactor-test")
  testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.1")
}

java {
  toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

tasks {
  withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
      jvmTarget = "17"
    }
  }
}
