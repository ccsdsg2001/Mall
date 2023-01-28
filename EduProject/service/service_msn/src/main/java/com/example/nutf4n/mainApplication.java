package com.example.nutf4n;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author cc
 * @date 2023年01月28日 0:17
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan({"com.example"})
public class mainApplication {
     public static void main(String[] args) {
           SpringApplication.run(mainApplication.class, args);
      }

}
