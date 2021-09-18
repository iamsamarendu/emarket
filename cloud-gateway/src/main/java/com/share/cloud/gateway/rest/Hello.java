package com.share.cloud.gateway.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {
    @GetMapping("/")
    public String welcome(){
        return "Welcome to Cloud GateWay";
    }
}
