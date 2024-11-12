FROM gradle:8.8-alpine AS builder

WORKDIR /app

COPY gradle /app/gradle
COPY gradlew /app/gradlew
COPY build.gradle /app/build.gradle
COPY settings.gradle /app/settings.gradle

COPY common /app/common
COPY gateway-config /app/gateway-config
COPY log-config /app/log-config
COPY file-service /app/file-service

RUN chmod +x ./gradlew

RUN ./gradlew clean :file-service:build -x test

FROM openjdk:21-jdk-slim

WORKDIR /app

COPY --from=builder /app/file-service/build/libs/file-service-0.0.1-SNAPSHOT.jar /app/file-service.jar

ENTRYPOINT ["java", "-jar", "/app/file-service.jar"]