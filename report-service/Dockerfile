FROM gradle:8.8-alpine AS builder

WORKDIR /app

COPY gradle /app/gradle
COPY gradlew /app/gradlew
COPY build.gradle /app/build.gradle
COPY settings.gradle /app/settings.gradle

COPY report-service /app/report-service

RUN chmod +x ./gradlew

RUN ./gradlew clean :report-service:build -x test

FROM openjdk:21-jdk-slim

WORKDIR /app

COPY --from=builder /app/report-service/build/libs/report-service-0.0.1-SNAPSHOT.jar /app/report-service.jar

ENTRYPOINT ["java", "-jar", "/app/report-service.jar"]