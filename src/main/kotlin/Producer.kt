import com.rabbitmq.client.ConnectionFactory
import kotlinx.coroutines.*
import kotlin.random.Random

object Producer {
    fun runProducer() = runBlocking {
        val factory = ConnectionFactory().apply {
            host = Config.rabbitmqHost
            port = Config.rabbitmqPort
            username = Config.rabbitmqUsername
            password = Config.rabbitmqPassword
        }

        factory.newConnection().use { connection ->
            val jobs = List(1) { // Anzahl der parallel laufenden Coroutines
                launch(Dispatchers.Default) {
                    connection.createChannel().use { channel ->
                        val queueName = Config.inputQueue
                        channel.queueDeclare(queueName, true, false, false, null)

                        while (true) {
                            val data = IntArray(10000) { Random.nextInt(0, 100) }
                            val message = data.joinToString(",")

                            channel.basicPublish("", queueName, null, message.toByteArray())
                        }
                    }
                }
            }

            jobs.forEach { it.join() }
        }
    }
}
