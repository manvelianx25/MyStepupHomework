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
}

// Запуск тестов требует сначала скомпилировать код и тесты
tasks.test {
    useJUnitPlatform()
    dependsOn(tasks.classes, tasks.compileTestJava)
    testLogging {
        events("passed", "failed")
        showStandardStreams = true // чтобы строки TEST PASSED/FAILED были видны в отчёте Gradle
    }
    doLast {
        println("Test run is over")
    }
}

// Кастомный таск: build автоматически запускает проверку тестами
tasks.build {
    dependsOn(tasks.test)
}