package com.example.nutf4n;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author cc
 * @date 2023年01月27日 15:06
 */
@SpringBootApplication
@ComponentScan({"com.example"})
public class applicationMAIN {
     public static void main(String[] args) {
           SpringApplication.run(applicationMAIN.class, args);
      }
     
}
