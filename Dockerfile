FROM openjdk:17-jdk-buster
ARG JAR_FILE=target/Hackathon-Test-Task-Backend-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
CMD ["java", "-jar", "app.jar"]
