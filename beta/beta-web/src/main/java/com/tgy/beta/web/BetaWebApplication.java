package com.tgy.beta.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author DragonSwimDiving
 * @program beta
 * @Date 2020-07-02 15:19
 **/
@SpringBootApplication(scanBasePackages="com.tgy.beta")
@MapperScan("com.tgy.beta.dao.mapper")
public class BetaWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(BetaWebApplication.class,args);
    }
}
