FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
COPY ./webapp/target/webapp-0.0.1-SNAPSHOT.jar webapp.jar
EXPOSE 8081
ENTRYPOINT exec java $JAVA_OPTS -jar webapp.jar
