package com.example.guliMall.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author cc
 * @date 2023年02月18日 13:05
 */
@SpringBootApplication
@MapperScan("com.example.guliMall.product.dao")
public class ProduceApplication {
     public static void main(String[] args) {
           SpringApplication.run(ProduceApplication.class, args);
      }

}
