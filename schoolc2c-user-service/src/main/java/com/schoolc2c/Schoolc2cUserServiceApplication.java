package com.schoolc2c;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.schoolc2c.user.mapper")
public class Schoolc2cUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(Schoolc2cUserServiceApplication.class, args);
    }

}
