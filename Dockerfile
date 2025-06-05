FROM openjdk:17-jdk-slim

WORKDIR /app
COPY build/libs/*.jar app.jar
COPY src/main/resources/application.properties /app/config/

ENTRYPOINT ["java", "-jar", "-Dspring.config.location=file:/app/config/application.properties", "app.jar"]