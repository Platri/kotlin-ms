import com.typesafe.config.ConfigFactory

object Config {
    private val config = ConfigFactory.load()

    val rabbitmqHost = config.getString("rabbitmq.host")
    val rabbitmqPort = config.getInt("rabbitmq.port")
    val rabbitmqUsername = config.getString("rabbitmq.username")
    val rabbitmqPassword = config.getString("rabbitmq.password")
    val inputQueue = config.getString("rabbitmq.queues.input")
    val outputQueue = config.getString("rabbitmq.queues.output")
}
