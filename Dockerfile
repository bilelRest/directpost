<<<<<<< HEAD
# Replace the deprecated openjdk line with this:
FROM eclipse-temurin:17-jdk-jammy

=======
FROM openjdk:17-jdk-slim
>>>>>>> b131ec60a55e4e49fa7da1df8ca94c42bf6f68c3
WORKDIR /app

# The rest of your file remains the same
COPY target/myship-0.0.1-SNAPSHOT.jar /app/myship-0.0.1-SNAPSHOT.jar

# Optional: If your jar is built to look for config in this specific path
COPY src/main/resources/application.properties /app/src/main/resources/application.properties

EXPOSE 6161

CMD ["java", "-jar", "myship-0.0.1-SNAPSHOT.jar"]