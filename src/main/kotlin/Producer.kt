import com.rabbitmq.client.ConnectionFactory

object Producer {
    fun runProducer() {
        val factory = ConnectionFactory().apply {
            host = Config.rabbitmqHost
            port = Config.rabbitmqPort
            username = Config.rabbitmqUsername
            password = Config.rabbitmqPassword
        }

        factory.newConnection().use { connection ->
            connection.createChannel().use { channel ->
                val queueName = "numbers"
                channel.queueDeclare(queueName, true, false, false, null)

                while (true) {
                    val data = IntArray(10000) { kotlin.random.Random.nextInt(0, 10) }
                    val message = data.joinToString(",")

                    channel.basicPublish("", queueName, null, message.toByteArray())
                    println("Sent: $message")
                    Thread.sleep(5) // Wait for 5 seconds before sending the next message
                }
            }
        }
    }
}
