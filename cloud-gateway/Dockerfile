FROM openjdk:11
MAINTAINER samarendu
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} cloud-gateway.jar
ENTRYPOINT ["java","-jar","/cloud-gateway.jar"]
