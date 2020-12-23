package com.transmission.transmission;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
//@MapperScan(value="com.transmission.transmission.dao")
public class TransmissionApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransmissionApplication.class, args);
    }


}
