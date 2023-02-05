package com.example.eduorder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author cc
 * @date 2023年01月31日 18:40
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.example"})
@MapperScan("com.example.eduorder.mapper")
@EnableFeignClients
@EnableDiscoveryClient
public class OrderApplication {
     public static void main(String[] args) {
           SpringApplication.run(OrderApplication.class, args);
      }

}
