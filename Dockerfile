FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src

RUN mvn install -DskipTests
FROM openjdk:17-alpine
WORKDIR /app

COPY --from=build /app/target/Hackathon-Test-Task-Backend-0.0.1-SNAPSHOT.jar .
CMD ["java", "-jar", "Hackathon-Test-Task-Backend-0.0.1-SNAPSHOT.jar"]