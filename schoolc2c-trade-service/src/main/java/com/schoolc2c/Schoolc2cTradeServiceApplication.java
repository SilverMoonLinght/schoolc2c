package com.schoolc2c;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.schoolc2c.trade.mapper")
public class Schoolc2cTradeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(Schoolc2cTradeServiceApplication.class, args);
    }

}
