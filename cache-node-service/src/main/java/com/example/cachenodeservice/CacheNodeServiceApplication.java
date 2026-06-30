package com.example.cachenodeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CacheNodeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CacheNodeServiceApplication.class, args);
    }

}
