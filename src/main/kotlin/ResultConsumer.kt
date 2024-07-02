import com.rabbitmq.client.ConnectionFactory

object ResultConsumer {
    fun runConsumer() {
        val factory = ConnectionFactory().apply {
            host = Config.rabbitmqHost
            port = Config.rabbitmqPort
            username = Config.rabbitmqUsername
            password = Config.rabbitmqPassword
        }

        factory.newConnection().use { connection ->
            connection.createChannel().use { channel ->
                val queueName = "results"
                channel.queueDeclare(queueName, true, false, false, null)

                val deliverCallback = { _: String, delivery: com.rabbitmq.client.Delivery ->
                    val message = String(delivery.body, Charsets.UTF_8)
                    println("Received: $message")
                }

                channel.basicConsume(queueName, true, deliverCallback) { _ -> }

                println(" [*] Waiting for messages. To exit press CTRL+C")
                while (true) {
                    Thread.sleep(1000)
                }
            }
        }
    }
}
