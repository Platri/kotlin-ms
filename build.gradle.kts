plugins {
    kotlin("jvm") version "1.8.0" // Aktualisieren Sie auf die neueste stabile Version von Kotlin
    application
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.8.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("com.rabbitmq:amqp-client:5.13.0")
    implementation("ch.qos.logback:logback-classic:1.2.11")
    implementation("com.typesafe:config:1.4.2")
    implementation("io.github.cdimascio:dotenv-kotlin:6.4.1")
}

application {
    mainClass.set("MainKt")
}

tasks.withType<JavaExec> {
    environment(System.getenv())
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = application.mainClass.get()
    }
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
