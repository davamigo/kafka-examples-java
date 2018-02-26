import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.Random;

/**
 * Kafka producer demo - main class
 *
 * See: https://kafka.apache.org/documentation/#producerconfigs
 *
 * @author david.amigo
 */
public class KafkaProducerDemo {

    /**
     * Kafka producer demo
     *
     * @param args The commandline arguments
     */
    public static void main(String[] args) {

        // Kafka producer properties
        Properties properties = new Properties();

        // Kafka bootstrap server property
        properties.setProperty("bootstrap.servers", "127.0.0.1:9092");
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", StringSerializer.class.getName());

        // Producer acks property
        properties.setProperty("acks", "1");
        properties.setProperty("retries", "3");

        // Auto flush messager every 1 ms
        properties.setProperty("linger.ms", "1");

        // The producer is the object used to write messages to Kafka
        Producer<String, String> producer =
                new org.apache.kafka.clients.producer.KafkaProducer<String, String>(properties);

        String key = Integer.toString((new Random()).nextInt(10));
        String value = "Message test #" + key;

        // The producer record contains the message to send to Kafka
        ProducerRecord<String, String> message =
                new ProducerRecord<String, String>("first_topic", key, value);

        // Send the message to Kafka
        producer.send(message);

        // Flush the producer to actually send the message to kafka
        // (override by "linger.ms" property)
        //producer.flush();

        // Close the connection with Kafka
        producer.close();
    }
}
