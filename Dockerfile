FROM openjdk:11

ADD target/axis-app.jar app.jar

EXPOSE 9001

ENTRYPOINT [ "java", "-jar", "app.jar" ]