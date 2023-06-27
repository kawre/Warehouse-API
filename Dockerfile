FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY ./target/Magazyn-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "Magazyn-0.0.1-SNAPSHOT.jar"]