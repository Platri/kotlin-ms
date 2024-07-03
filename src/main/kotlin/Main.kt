import kotlinx.coroutines.*
import io.github.cdimascio.dotenv.Dotenv

fun main() = runBlocking {
    val dotenv = Dotenv.load() // Verwenden Sie Dotenv.load() anstelle von dotenv {} für Einfachheit

    // Setzen Sie die Umgebungsvariablen aus der .env-Datei
    dotenv.entries().forEach { 
        System.setProperty(it.key, it.value)
    }

    // Beispiel: Lesen Sie eine Umgebungsvariable und drucken Sie sie aus
    val rabbitmqHost = System.getProperty("RABBITMQ_HOST")
    println("RabbitMQ Host: $rabbitmqHost")
    
    val jobProducer = launch(Dispatchers.Default) { Producer.runProducer() }
    val jobConsumer = launch(Dispatchers.Default) { ResultConsumer.runConsumer() }

    joinAll(
        jobProducer, 
        jobConsumer
    )
}
