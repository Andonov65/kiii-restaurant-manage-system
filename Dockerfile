FROM openjdk:15-alpine
EXPOSE 8080
COPY UgostitelskiObjekti-0.0.1-SNAPSHOT.jar ugostitelski-objekti.jar
CMD ["java", "-jar", "ugostitelski-objekti.jar"]