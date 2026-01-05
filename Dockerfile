// Source - https://stackoverflow.com/a
// Posted by Santanu, modified by community. See post 'Timeline' for change history
// Retrieved 2026-01-05, License - CC BY-SA 4.0

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY src ./src
COPY target//myship-0.0.1-SNAPSHOT.jar /app/myship-0.0.1-SNAPSHOT.jar
COPY src/main/resources/application.properties /app/src/main/resources/application.properties


EXPOSE 6161


CMD ["java", "-jar", "myship-0.0.1-SNAPSHOT.jar"]
