plugins {
    kotlin("jvm") version "1.8.0" // Aktualisieren Sie auf die neueste stabile Version von Kotlin
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("com.rabbitmq:amqp-client:5.13.0")
    implementation("ch.qos.logback:logback-classic:1.2.11")
    implementation("com.typesafe:config:1.4.2")
}

application {
    mainClass.set("MainKt")
}
