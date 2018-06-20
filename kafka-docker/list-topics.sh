#!/bin/bash

COMMAND="docker exec -it \
    docker_kafka_dev \
    bin/kafka-topics.sh \
        --list \
        --zookeeper localhost:2181";

echo -en "\n$ ";
echo -e $COMMAND;

$COMMAND
echo -e "";
