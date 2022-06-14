# Start with base image
FROM openjdk
# Add a temporary volume
VOLUME /tmp

ARG JAR_FILE=target/app_0.0.1.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 80