plugins {
    `java-library`
    `maven-publish`
    id("io.papermc.paperweight.userdev") version "2.0.0-beta.19"
    id("com.gradleup.shadow") version "9.2.2"
}

group = "com.github.justadeni"
version = "1.5.0"
description = "Tree cutter plugin with physics."

val javaVersion = 21

java {
    toolchain.languageVersion = JavaLanguageVersion.of(javaVersion)
}

repositories {
    mavenCentral()
}

dependencies {
    paperweight.paperDevBundle("1.21.8-R0.1-SNAPSHOT")
}

tasks {
    compileJava {
        options.release.set(javaVersion)
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifact(tasks.shadowJar)
        }
    }
}
