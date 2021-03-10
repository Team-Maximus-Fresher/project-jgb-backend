FROM openjdk:11

ADD build/libs/jgb-backend-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 9001

ENTRYPOINT [ "java", "-jar", "app.jar" ]