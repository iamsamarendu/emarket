FROM openjdk:8-jdk-alpine
RUN apk add --no-cache curl tar bash procps
MAINTAINER samarendu
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} eureka-discovery.jar
ENTRYPOINT ["java","-jar","/eureka-discovery.jar"]
