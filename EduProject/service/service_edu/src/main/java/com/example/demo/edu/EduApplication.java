package com.example.demo.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author cc
 * @date 2023年01月15日 19:24
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.example"})
public class EduApplication {
     public static void main(String[] args) {
           SpringApplication.run(EduApplication.class, args);
      }

}
