FROM gradle:8.8-alpine AS builder

VOLUME /home/gradle/.gradle

WORKDIR /app

COPY gradle /app/gradle
COPY gradlew /app/gradlew
COPY build.gradle /app/build.gradle
COPY settings.gradle /app/settings.gradle

COPY common /app/common
COPY gateway-config /app/gateway-config
COPY report-service /app/report-service

RUN chmod +x ./gradlew

COPY common /app/common
COPY gateway-config /app/gateway-config
COPY log-config /app/log-config
RUN ./gradlew clean :report-service:build -x test

FROM openjdk:21-jdk-slim

WORKDIR /app

COPY --from=builder /app/report-service/build/libs/report-service-0.0.1-SNAPSHOT.jar /app/report-service.jar

ENTRYPOINT ["java", "-jar", "/app/report-service.jar"]