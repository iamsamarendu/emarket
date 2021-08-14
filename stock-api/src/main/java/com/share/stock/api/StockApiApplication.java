package com.share.stock.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class StockApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(StockApiApplication.class,args);
    }
}
