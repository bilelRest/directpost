FROM openjdk:17

WORKDIR /app

COPY src ./src
COPY target//myship-0.0.1-SNAPSHOT.jar /app/myship-0.0.1-SNAPSHOT.jar
COPY src/main/resources/application.properties /app/src/main/resources/application.properties


EXPOSE 6161


CMD ["java", "-jar", "myship-0.0.1-SNAPSHOT.jar"]
