# build
FROM maven:3.6.2-jdk-8-slim AS build
COPY day-off-planner-rest/src /app/day-off-planner-rest/src
COPY day-off-planner-rest/pom.xml /app/day-off-planner-rest/pom.xml
COPY day-off-planner-rest-api/src /app/day-off-planner-rest-api/src
COPY day-off-planner-rest-api/pom.xml /app/day-off-planner-rest-api/pom.xml
COPY pom.xml /app/pom.xml
RUN mvn -f /app/pom.xml clean package

# package
FROM openjdk:8-jdk-alpine
COPY --from=build /app/day-off-planner-rest/target/day-off-planner-rest-*.jar /app/day-off-planner-rest.jar
COPY Docker/entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

EXPOSE 9090

ENTRYPOINT ["sh", "/entrypoint.sh"]
