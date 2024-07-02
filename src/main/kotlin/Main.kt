import kotlinx.coroutines.*
import com.rabbitmq.client.ConnectionFactory

fun main() = runBlocking {
    val jobProducer = launch(Dispatchers.Default) { Producer.runProducer() }
    val jobConsumer = launch(Dispatchers.Default) { ResultConsumer.runConsumer() }

    joinAll(jobProducer, jobConsumer)
}
