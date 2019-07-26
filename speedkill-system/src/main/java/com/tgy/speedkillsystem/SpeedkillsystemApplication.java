package com.tgy.speedkillsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.tgy.speedkillsystem.mapper")
@SpringBootApplication
public class SpeedkillsystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpeedkillsystemApplication.class, args);
    }

}
