package com.woniu.woniuticket.cinema;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan(value = "com.woniu.woniuticket.cinema.dao")
@EnableEurekaClient
@EnableFeignClients
@EnableCaching
@EnableScheduling //开启任务调度
public class CinemaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CinemaApplication.class, args);
    }

}
