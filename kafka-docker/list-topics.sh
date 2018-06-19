#!/bin/bash

docker exec -it \
    docker_kafka_dev \
    bin/kafka-topics.sh \
        --list \
        --zookeeper localhost:2181
