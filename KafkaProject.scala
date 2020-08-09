package ca.rohith.bigdata.kafka

import java.time.Duration
import java.util.Properties

import org.apache.kafka.clients.consumer.ConsumerConfig._
import org.apache.kafka.clients.consumer.{ConsumerRecord, ConsumerRecords, KafkaConsumer}
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}
import org.apache.kafka.common.serialization.{IntegerDeserializer, IntegerSerializer, StringDeserializer, StringSerializer}

import scala.collection.JavaConverters._

object KafkaProject extends App {
  val consumerProperties = new Properties()
  consumerProperties.setProperty(BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
  consumerProperties.setProperty(GROUP_ID_CONFIG, "consumer-group-1")
  consumerProperties.setProperty(KEY_DESERIALIZER_CLASS_CONFIG, classOf[IntegerDeserializer].getName)
  consumerProperties.setProperty(VALUE_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer].getName)
  consumerProperties.setProperty(AUTO_OFFSET_RESET_CONFIG, "earliest")

  //  The below code snippet is for a consumer subscribing to a topic
  val consumer = new KafkaConsumer[Int, String](consumerProperties)
  consumer.subscribe(List("trip").asJava)

  //  The below code snippet is for producing the values in kafka to the above subscribed consumer
  val topic = "enriched_trip"
  val producerProperties = new Properties()
  producerProperties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
  producerProperties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, classOf[IntegerSerializer].getName)
  producerProperties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer].getName)
  val producer = new KafkaProducer[Int, String](producerProperties)

  println("Key | Value | Partition | Offset")
  while (true) {
    val polledRecords: ConsumerRecords[Int, String] = consumer.poll(Duration.ofSeconds(1))
    if (!polledRecords.isEmpty) {
      println(s"Polled ${polledRecords.count()} records")
      val recordIterator = polledRecords.iterator()
      while (recordIterator.hasNext) {
        val record: ConsumerRecord[Int, String] = recordIterator.next()
        val a = record.value().split(",", -1)
        val parseTrip: Trip = Trip(a(0).toInt, a(1), a(2), a(3), a(4), a(5), a(6).toInt,
          if (a(7).isEmpty) None else Some(a(7)), if (a(8).isEmpty) None else Some(a(8)))
        val enrichedTrip = EnrichedTrip(parseTrip, None, None)
        val out = Convert.toCsv(enrichedTrip)
        println(out)

        producer.send(new ProducerRecord[Int, String](topic, out))
        producer.flush()
      }
    }
  }
  producer.close()
}