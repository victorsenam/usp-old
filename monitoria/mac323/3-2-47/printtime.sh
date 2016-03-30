#!/bin/bash
SECS=60
while [[ 0 -ne $SECS ]]; do
    echo "$SECS.."
    sleep 1
    SECS=$[$SECS-1]
done
echo "Time is up, clown."
