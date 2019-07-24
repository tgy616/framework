package com.tgy.pay;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author DragonSwimDiving
 * @program activemqdemo
 * @Date 2019-07-12 14:26
 **/
@SpringBootApplication
@MapperScan("com.tgy.pay.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

