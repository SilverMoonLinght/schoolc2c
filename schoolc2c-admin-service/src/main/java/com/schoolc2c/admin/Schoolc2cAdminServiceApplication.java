package com.schoolc2c.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.schoolc2c.admin.mapper")
public class Schoolc2cAdminServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(Schoolc2cAdminServiceApplication.class, args);
    }

}
