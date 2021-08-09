FROM openjdk:11
MAINTAINER samarendu
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} eureka-discovery.jar
ENTRYPOINT ["java","-jar","/eureka-discovery.jar"]
