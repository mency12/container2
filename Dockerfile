FROM openjdk:11-jre-slim

WORKDIR /app

COPY target/container2-1.0-SNAPSHOT.jar /app/app.jar

EXPOSE 8081

CMD ["java", "-jar", "app.jar"]