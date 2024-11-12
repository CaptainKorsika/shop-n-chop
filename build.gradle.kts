import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import io.gitlab.arturbosch.detekt.Detekt
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

buildscript {
    extra["springCloudVersion"] = "2023.0.3"
    extra["togglzVersion"] = "4.4.0"
    extra["json-path.version"] = "2.9.0"
    extra["archunitVersion"] = "1.3.0"
    extra["jvmTargetVersion"] = "21"
    extra["kotlinVersion"] = "1.9.23"
    extra["springBootVersion"] = "3.3.2"
    extra["detektVersion"] = "1.23.7"
}

plugins {
    val kotlinPluginVersion = "2.0.21"
    val springBootPluginVersion ="3.3.5"
    val detektPluginVersion = "1.23.7"

    id("org.springframework.boot") version springBootPluginVersion
    id("io.spring.dependency-management") version "1.1.6"
    id("io.gitlab.arturbosch.detekt") version detektPluginVersion
    id("info.solidsoft.pitest") version "1.15.0"

    kotlin("jvm") version kotlinPluginVersion
    kotlin("plugin.spring") version kotlinPluginVersion
}

group = "de"
version = "1.0-SNAPSHOT"

java {
    toolchain {
        val jvmTargetVersion: String by project
        languageVersion = JavaLanguageVersion.of(jvmTargetVersion)
    }
}



tasks.withType<KotlinCompile> {
    compilerOptions {
        val jvmTargetVersion: String by project
        jvmTarget.set(JvmTarget.fromTarget(jvmTargetVersion))
        freeCompilerArgs.add("-Xjsr305=strict")
    }
}

detekt {
    source.from(files("buildSrc/src", "src"))
    config.from(files("$projectDir/etc/detekt/detekt.yml"))
    autoCorrect = true
}

tasks.withType<Detekt> {
    exclude("**/resources/**", "**/build/**")
    reports {
        html {
            required.set(true)
            outputLocation.set(file("$projectDir/build/reports/detekt/detekt.html"))
        }
    }
}

repositories {
    mavenCentral()
}

dependencies {
    val springCloudVersion: String by project
    val togglzVersion: String by project
    val archunitVersion: String by project
    val detektVersion: String by project

    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:$detektVersion")

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation(platform("org.springframework.cloud:spring-cloud-dependencies:${springCloudVersion}"))
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework:spring-aspects")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("com.google.guava:guava:33.3.1-jre")
    testImplementation("io.mockk:mockk:1.13.13")
    testImplementation("io.kotest:kotest-assertions-core-jvm:5.9.1")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("io.mockk:mockk:1.13.13")
    testImplementation("com.ninja-squad:springmockk:4.0.2")
    testImplementation("io.kotest:kotest-assertions-core-jvm:5.9.1")
    testImplementation("org.awaitility:awaitility:4.2.2")
    testImplementation("com.atlassian.oai:swagger-request-validator-restassured:2.43.0")
}

dependencyManagement {
    dependencies {
        // REMOVE when spring-reactor has a netty version > 1.1.12
        dependency("io.netty:netty-codec-http:4.1.114.Final")
        // REMOVE when spring security has nimbus-jose-jwt >= 9.37.2
        dependency("com.nimbusds:nimbus-jose-jwt:9.41.2")
    }

    configurations.matching { it.name == "detekt" }.all {
        resolutionStrategy.eachDependency {
            if (requested.group == "org.jetbrains.kotlin") {
                useVersion(io.gitlab.arturbosch.detekt.getSupportedKotlinVersion())
            }
        }
    }
}

tasks.test {
    useJUnitPlatform()
}