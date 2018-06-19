#!/bin/bash

docker exec -it \
    docker_kafka_dev \
    bin/kafka-topics.sh \--delete \
        --topic my-first-topic \
        --zookeeper localhost:2181
