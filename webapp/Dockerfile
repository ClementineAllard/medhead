FROM openjdk:17-jdk-alpine
WORKDIR /webapp
COPY ./webapp/target/webapp-0.0.1-SNAPSHOT.jar webapp.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "webapp.jar"]
