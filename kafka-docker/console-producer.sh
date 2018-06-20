#!/bin/bash

COMMAND="docker exec -it \
    docker_kafka_dev \
    bin/kafka-console-producer.sh \
        --topic my-first-topic \
        --broker-list localhost:9092
        --property parse.key=true \
        --property key.separator=,";

echo -en "\n$ ";
echo -e $COMMAND;

$COMMAND
echo -e "";
