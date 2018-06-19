import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

/**
 * Kafka consumer demo - main class
 *
 * See: https://kafka.apache.org/documentation/#consumerconfigs
 *
 * @author david.amigo
 */
public class KafkaConsumerDemo {

    private static final String TOPIC = KafkaProducerDemo.TOPIC;
    private static final String GROUP = "group-1";

    /**
     * Kafka consumer demo
     *
     * @param args The commandline arguments
     */
    public static void main(String[] args) {

        // Kafka consumer properties
        Properties properties = new Properties();

        // Kafka bootstrap server property: "bootstrap.servers", "key.deserializer" & "value.deserializer"
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        // The "group.id" identifies the consumer group this consumer belongs to
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, GROUP);

        // Enable auto commit offsets every 1 second
        properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        properties.setProperty(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");

        // What to do when there is no initial offset in Kafka: "auto.offset.reset"="earliest"
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        // The consumer is the object used to read messages from Kafka
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(properties);
        kafkaConsumer.subscribe(Arrays.asList(TOPIC));

        // Infinite loop
        while (true) {
            ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(100);
            for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                System.out.println(
                        "> Message consumed: Topic=" + consumerRecord.topic() +
                        ", Partition=" + consumerRecord.partition() +
                        ", Offset=" + consumerRecord.offset() +
                        ", Timestamp=" + (new Date((long) consumerRecord.timestamp())).toString() +
                        ", Key=" + consumerRecord.key() +
                        ", Value=" + consumerRecord.value()
                );
            }

            // Commit read offsets
            // (override by "enable.auto.commit")
            //kafkaConsumer.commitSync();
        }
    }
}
