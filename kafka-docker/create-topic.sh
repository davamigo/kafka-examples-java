#!/bin/bash

docker exec -it \
    docker_kafka_dev \
    bin/kafka-topics.sh \
        --create \
        --topic my-first-topic \
        --partitions 7 \
        --replication-factor 1 \
        --zookeeper localhost:2181