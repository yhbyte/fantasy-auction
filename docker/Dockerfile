FROM gradle:8.9.0-jdk21 AS builder

WORKDIR /app
# Copy only the necessary files first to take advantage of Docker layer caching
COPY ../build.gradle settings.gradle /app/

RUN gradle build -i --no-daemon || return 0

COPY .. /app

RUN gradle build --no-daemon -x test

FROM openjdk:21-slim
WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]