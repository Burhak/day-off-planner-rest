#!/bin/bash
echo "enterpointing..."

HOST="docker.host.internal"
if [[ -n "$1" ]]; then
  HOST="$1"
fi

DB_USER=""
if [[ -n "$2" ]]; then
  DB_USER="-Dspring.datasource.username=$2"
fi

DB_PASS=""
if [[ -n "$3" ]]; then
  DB_PASS="-Dspring.datasource.password=$3"
fi

echo "Running with host: $HOST"

java -jar -Dhost="$HOST" "$DB_USER" "$DB_PASS" /app/day-off-planner-rest.jar
