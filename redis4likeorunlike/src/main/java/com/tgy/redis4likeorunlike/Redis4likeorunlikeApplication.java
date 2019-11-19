package com.tgy.redis4likeorunlike;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class Redis4likeorunlikeApplication {

    public static void main(String[] args) {
        SpringApplication.run(Redis4likeorunlikeApplication.class, args);
    }

}
