# Kafka and Kafka Streams Proofs of Concept

## kafka-poc/kafka-examples-java

Basic examples using Kafka.

* `KafkaProducerDemo`: Basic producer example.

* `KafkaConsumerDemo`: Basic consumer example.

Configuration:

* brokers: **127.0.0.1:9092**
* topic: **my-first-topic**
* group id: **group-1**

### Example commands:

#### Create docker container with Zookeeper + Kafka.

```
$ docker run --rm -d \
    -p 9092:9092 \
    -p 2181:2181 \
    -e KAFKA_ADVERTISED_HOST_NAME=localhost \
    --name docker_kafka_dev \
    blacktop/kafka
```

#### Enter in a bash session in the docker container.

```
docker exec -it docker_kafka_dev bash
```

#### Create "my-tirst-topic" topic with 7 partitions.

```
bin/kafka-topics.sh --create \
    --topic my-first-topic \
    --partitions 7 \
    --replication-factor 1 \
    --zookeeper localhost:2181
```

#### List all the topics:

```
bin/kafka-topics.sh --list \
    --zookeeper localhost:2181
```

#### Run producer.

```
bin/kafka-console-producer.sh \
    --topic my-first-topic \
    --broker-list localhost:9092

hello
world
privalia
privalia-tech
engineering-culture
<Ctrl + C>
```

#### Run the consumer.

```
bin/kafka-console-consumer.sh \
    --topic my-first-topic \
    --bootstrap-server localhost:9092 \
    --from-beginning

<Ctrl+C>
```

#### Stop the container.

```
docker stop docker_kafka_dev
```
