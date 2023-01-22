package com.example.demo.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author cc
 * @date 2023年01月21日 14:35
 */

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = {"com.example"})
public class OssAplication {
     public static void main(String[] args) {
           SpringApplication.run(OssAplication.class, args);
      }
     
    
}
