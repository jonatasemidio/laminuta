package com.laminuta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;

@SpringBootApplication
@RestController
public class Main {

    @Value("${config.message}")
    private String message;

    @GetMapping("/hello")
    public String hello() {
        return message;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
