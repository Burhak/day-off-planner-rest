#!/bin/bash
echo "enterpointing..."

HOST="docker.host.internal"
if [[ -n "$1" ]]; then
  HOST="$1"
fi

echo "Running with host: $HOST"

java -jar -Dhost="$HOST" /app/day-off-planner-rest.jar
