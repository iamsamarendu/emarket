package com.share.company.api;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDynamoDBRepositories
@EnableEurekaClient
@EnableFeignClients
@EnableCircuitBreaker
public class CompanyApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(CompanyApiApplication.class, args);
    }
}
