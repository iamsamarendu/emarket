package com.share.company.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
@EnableEurekaClient
@EnableFeignClients
@EnableCircuitBreaker
public class CompanyApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(CompanyApiApplication.class, args);
    }
}
