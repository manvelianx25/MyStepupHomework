
import org.gradle.api.tasks.testing.Test

plugins {
    id("java")
}

group = "com.mystepup"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    testImplementation("org.assertj:assertj-core:3.27.7")
    implementation("io.rest-assured:rest-assured:5.5.6")
    testImplementation("com.fasterxml.jackson.core:jackson-databind:2.21.7")
}

// Запуск всех тестов с возможностью фильтрации по тегу через -PtagName
tasks.test {
    systemProperty("file.encoding", "UTF-8")
    environment("JAVA_TOOL_OPTIONS", "-Dfile.encoding=UTF-8")

    useJUnitPlatform {
        if (project.hasProperty("tagName")) {
            includeTags(project.property("tagName") as String)
        }
    }

    dependsOn(tasks.classes, tasks.compileTestJava)

    testLogging {
        events("passed", "failed")
        showStandardStreams = true
    }

    doLast {
        println("Test run is over")
    }
}

// Отдельная задача только для API-автотестов с тегом @Tag("api")
tasks.register<Test>("apiTest") {
    description = "Runs API autotests only"
    group = "verification"

    dependsOn(tasks.named("testClasses"))

    useJUnitPlatform {
        includeTags("api")
    }

    testClassesDirs = sourceSets["test"].output.classesDirs
    classpath = sourceSets["test"].runtimeClasspath

    systemProperty("file.encoding", "UTF-8")
    environment("JAVA_TOOL_OPTIONS", "-Dfile.encoding=UTF-8")

    testLogging {
        events("passed", "failed")
        showStandardStreams = true
    }
}

// build запускает обычную задачу test
tasks.build {
    dependsOn(tasks.test)
}
