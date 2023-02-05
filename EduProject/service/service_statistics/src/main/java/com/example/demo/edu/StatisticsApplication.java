package com.example.demo.edu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

/**
 * @author cc
 * @date 2023年02月01日 21:10
 */
@SpringBootApplication
@ComponentScan({"com.example"})
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.example.demo.edu.mapper")
@EnableScheduling
public class StatisticsApplication {
     public static void main(String[] args) {
           SpringApplication.run(StatisticsApplication.class, args);
      }

}
