package com.example.vod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author cc
 * @date 2023年01月25日 23:32
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = {"com.example"})
public class vodapplication {
    
     public static void main(String[] args) {
           SpringApplication.run(vodapplication.class, args);
      }
     
}
