import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
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

    public static final String TOPIC = "my-first-topic";

    private static final String[] WORDS= {
        "hello",
        "world",
        "privalia",
        "privalia-tech",
        "engineering culture",
        "apache",
        "kafka",
        "spring",
        "spring-boot",
        "java"
    };

    /**
     * Kafka producer demo
     *
     * @param args The commandline arguments
     */
    public static void main(String[] args) {

        // Kafka producer properties
        Properties properties = new Properties();

        // Kafka configuration
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // Producer "acks" and "retries" properties
        properties.setProperty(ProducerConfig.ACKS_CONFIG, "1");
        properties.setProperty(ProducerConfig.RETRIES_CONFIG, "3");

        // Auto flush messager every 1 ms
        properties.setProperty(ProducerConfig.LINGER_MS_CONFIG, "1");

        // The producer is the object used to write messages to Kafka
        Producer<String, String> producer =
                new org.apache.kafka.clients.producer.KafkaProducer<String, String>(properties);

        int index = (new Random()).nextInt(WORDS.length);
        String key = Integer.toString(index);
        String value = WORDS[index];

        // The producer record contains the message to send to Kafka
        ProducerRecord<String, String> message =
                new ProducerRecord<String, String>(TOPIC, key, value);

        // Send the message to Kafka
        producer.send(message);

        System.out.println("> Message produced: Key=" + key + ", Value=" + value);

        // Flush the producer to actually send the message to kafka
        // (overrides by "linger.ms" property)
        //producer.flush();

        // Close the connection with Kafka
        producer.close();
    }
}
