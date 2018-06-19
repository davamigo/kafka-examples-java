#!/bin/bash

docker run --rm -d \
    -p 9092:9092 \
    -p 2181:2181 \
    -e KAFKA_ADVERTISED_HOST_NAME=localhost \
    --name docker_kafka_dev \
    blacktop/kafka

