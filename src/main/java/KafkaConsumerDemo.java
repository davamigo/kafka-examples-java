import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Arrays;
import java.util.Properties;

/**
 * Kafka consumer demo - main class
 *
 * See: https://kafka.apache.org/documentation/#consumerconfigs
 *
 * @author david.amigo
 */
public class KafkaConsumerDemo {

    /**
     * Kafka consumer demo
     *
     * @param args The commandline arguments
     */
    public static void main(String[] args) {

        // Kafka consumer properties
        Properties properties = new Properties();

        // Kafka bootstrap server property
        properties.setProperty("bootstrap.servers", "127.0.0.1:9092");
        properties.setProperty("key.deserializer", StringDeserializer.class.getName());
        properties.setProperty("value.deserializer", StringDeserializer.class.getName());

        // The consumer group id identifies the consumer group this consumer belongs to
        properties.setProperty("group.id", "test_1");

        // Enable auto commit offsets every 1 second
        properties.setProperty("enable.auto.commit", "true");
        properties.setProperty("auto.commit.interval.ms", "1000");

        // What to do when there is no initial offset in Kafka
        properties.setProperty("auto.offset.reset", "earliest");

        // The consumer is the object used to read messages from Kafka
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(properties);
        kafkaConsumer.subscribe(Arrays.asList("first_topic"));

        // Infinite loop
        while (true) {
            ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(100);
            for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                System.out.println(
                        "Topic: " + consumerRecord.topic() +
                        " Partition: " + consumerRecord.partition() +
                        " Offset: " + consumerRecord.offset() +
                        " Key: " + consumerRecord.key() +
                        " Value: " + consumerRecord.value() +
                        " Timestamp: " + consumerRecord.timestamp()
                );
            }

            // Commit read offsets
            // (override by "enable.auto.commit")
            //kafkaConsumer.commitSync();
        }
    }
}
