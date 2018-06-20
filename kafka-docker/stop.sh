#!/bin/bash

COMMAND="docker stop \
    docker_kafka_dev";

echo -en "\n$ ";
echo -e $COMMAND;

$COMMAND
echo -e "";
