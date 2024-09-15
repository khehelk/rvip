FROM gradle:8.8-alpine AS builder

WORKDIR /app

COPY gradle /app/gradle
COPY gradlew /app/gradlew
COPY build.gradle /app/build.gradle
COPY settings.gradle /app/settings.gradle

COPY gateway /app/gateway

RUN chmod +x ./gradlew

RUN ./gradlew clean :gateway:build -x test

FROM openjdk:21-jdk-slim

WORKDIR /app

COPY --from=builder /app/gateway/build/libs/gateway-0.0.1-SNAPSHOT.jar /app/gateway.jar

ENTRYPOINT ["java", "-jar", "/app/gateway.jar"]