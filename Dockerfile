FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app

# The rest of your file remains the same
COPY target/myship-0.0.1-SNAPSHOT.jar /app/myship-0.0.1-SNAPSHOT.jar

# Optional: If your jar is built to look for config in this specific path
COPY src/main/resources/application.properties /app/src/main/resources/application.properties

EXPOSE 6161

CMD ["java", "-jar", "myship-0.0.1-SNAPSHOT.jar"]