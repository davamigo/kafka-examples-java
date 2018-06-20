#!/bin/bash

COMMAND="docker exec -it \
    docker_kafka_dev \
    bin/kafka-console-consumer.sh \
        --topic my-first-topic \
        --bootstrap-server localhost:9092
        --from-beginning \
        --property print.key=true \
        --property print.value=true \
        --property key.separator=,";

echo -en "\n$ ";
echo -e $COMMAND;

$COMMAND
echo -e "";
