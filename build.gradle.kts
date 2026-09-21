plugins {
    java
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.1")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.10.1")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:1.10.1")
}

tasks.test {
    useJUnitPlatform()
    outputs.upToDateWhen { false }
    testLogging {
        events("standardOut", "standardError", "passed", "failed", "skipped")
    }
}

// ЗАДАЧА 1: Запускает все тесты
tasks.register("runAllTests") {
    description = "Запускает все тесты в проекте"
    dependsOn(tasks.test)
}

// ЗАДАЧА 2: Пишет "Test run is over" после завершения первой
tasks.register("notifyTestCompletion") {
    description = "Выводит сообщение об окончании прогона тестов"
    dependsOn("runAllTests")

    doLast {
        println("Test run is over")
    }
}