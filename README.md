# Emarket App
The goal of this project is to implement two [Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/) application, called `company-api` and `stock-api` to manage Comany and its stock details.  
Additionally there is a `Eureka-Discovery` server for client-side discovery and a `Cloud-Gateway` to use as api-gateway.

## Application
### company-api
`Spring Boot` Web Java application that exposes a REST API for managing companies. This app uses a `Feign` client to retrive info from the `stock-api` app.

### stock-api
`Spring Boot` Web Java application that exposes a REST API for managing stocks of companies.

## Prerequisites

* [`Java 8+`](https://www.oracle.com/java/technologies/javase-jdk8-downloads.html)
* [`Docker`](https://www.docker.com/)
* [`Docker-Compose`](https://docs.docker.com/compose/install/)

## Start Environment

* Open a terminal and inside root folder and run
  ```shell
  docker-compose up -d
  ```

* Wait a bit until containers be `Up (healthy)`. In order to check it run
  ```shell
  docker-compose ps
  ```
## Running application with Maven

- Open a terminal and make sure you are inside root folder

- Run the following command
  ```
  ./mvnw clean spring-boot:run --projects company-api
  
  ./mvnw clean spring-boot:run --projects stock-api
  
  ./mvnw clean spring-boot:run --projects eureka-discovery
  
  ./mvnw clean spring-boot:run --projects cloud-gateway
  ```  

## Application ports
* company-api : 8081
* stock-api : 8070
* eureka-discovery: 8075
* cloud-gateway: 8060

## Shutdown

- To stop application
    - If it was started with Maven, go to the terminals where it is running and press `Ctrl+C`
    - If it was started as a Docker container, go to a terminal and run the command below
      ```shell
      docker stop company-api
      
      docker stop stock-api
      
      ```
- To stop and remove `docker-compose` containers, network and volumes, go to a terminal and, inside root folder, run the following command
  ```
  docker-compose down -v
  ```

## Check DB
```sql
mysql> update user set host='%' where user='root';
mysql> flush privileges;
mysql> grant all privileges on *.* to 'root'@'localhost';
mysql> flush privileges;
```