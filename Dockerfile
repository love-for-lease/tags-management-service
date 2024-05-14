FROM openjdk:17-alpine

WORKDIR /app

COPY build/libs/tags-management-service-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 7000

ENTRYPOINT ["java", "-jar", "app.jar"]