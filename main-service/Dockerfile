FROM gradle:8.8-alpine AS builder

WORKDIR /app

COPY gradle /app/gradle
COPY gradlew /app/gradlew
COPY build.gradle /app/build.gradle
COPY settings.gradle /app/settings.gradle

COPY main-service /app/main-service

RUN ./gradlew clean :main-service:build -x test

FROM openjdk:21-jdk-slim

WORKDIR /app

COPY --from=builder /app/main-service/build/libs/main-service-0.0.1-SNAPSHOT.jar /app/main-service.jar

ENTRYPOINT ["java", "-jar", "/app/main-service.jar"]