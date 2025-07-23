FROM maven:3.9.6-eclipse-temurin-21 AS builder

COPY src /app/src
COPY pom.xml /app

WORKDIR /app
RUN mvn clean install -U -DskipTests

FROM openjdk:24-jdk-slim
COPY --from=builder /app/target/*.jar /app/app.jar
WORKDIR /app
EXPOSE 8084
CMD ["java", "-jar", "app.jar"]
