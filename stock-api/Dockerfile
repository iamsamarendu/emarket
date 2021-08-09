FROM openjdk:11
MAINTAINER samarendu
WORKDIR /app
# Add wait script to the image - script pulled from https://github.com/ufoscout/docker-compose-wait/releases/download/2.7.3/wait /wait
COPY /script/wait /app/
RUN chmod -R +x /app
#RUN apk --no-cache add curl
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /app/stock-api.jar
CMD ./wait && java -Djava.security.egd=file:/dev/./urandom -jar stock-api.jar