FROM openjdk:21

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} NGS_app.jar

ENTRYPOINT ["java", "-jar", "NGS_app.jar"]

EXPOSE 8091
