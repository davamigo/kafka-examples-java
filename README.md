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

Script: [`kafka-docker/start.sh`](kafka-docker/start.sh)

```
$ docker run --rm -d \
    -p 9092:9092 \
    -p 2181:2181 \
    -e KAFKA_ADVERTISED_HOST_NAME=localhost \
    --name docker_kafka_dev \
    blacktop/kafka
```

#### Enter in a bash session in the docker container.

Script: [`kafka-docker/bash.sh`](kafka-docker/bash.sh)

```
$ docker exec -it docker_kafka_dev bash
```

#### Create "my-tirst-topic" topic with 7 partitions.

Script: [`kafka-docker/create-topic.sh`](kafka-docker/create-topic.sh)

```
$ bin/kafka-topics.sh --create \
    --topic my-first-topic \
    --partitions 7 \
    --replication-factor 1 \
    --zookeeper localhost:2181
```

#### List all the topics:

Script: [`kafka-docker/list-topics.sh`](kafka-docker/list-topics.sh)

```
$ bin/kafka-topics.sh --list \
    --zookeeper localhost:2181
```

#### Run producer.

Script: [`kafka-docker/console-producer.sh`](kafka-docker/console-producer.sh)

```
$ bin/kafka-console-producer.sh \
    --topic my-first-topic \
    --broker-list localhost:9092
1,hello
2,world
3,privalia
4,privalia-tech
5,engineering-culture
<Ctrl + C>
```

#### Run the consumer.

Script: [`kafka-docker/console-consumer.sh`](kafka-docker/console-consumer.sh)


```
$ bin/kafka-console-consumer.sh \
    --topic my-first-topic \
    --bootstrap-server localhost:9092 \
    --from-beginning
3,privalia
4,privalia-tech
5,engineering culture
1,hello
2,world
<Ctrl+C>
```

#### Stop the container.

Script: [`kafka-docker/stop.sh`](kafka-docker/stop.sh)


```
$ docker stop docker_kafka_dev
```
