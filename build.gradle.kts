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
    // Source: https://mvnrepository.com/artifact/org.assertj/assertj-core
    testImplementation("org.assertj:assertj-core:3.27.7")
}

// Запуск тестов требует сначала скомпилировать код и тесты
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

// Кастомный таск: build автоматически запускает проверку тестами
tasks.build {
    dependsOn(tasks.test)
}