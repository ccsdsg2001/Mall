package com.example.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * @author cc
 * @date 2023年02月03日 19:15
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Gatewayapplication {
     public static void main(String[] args) {
           SpringApplication.run(Gatewayapplication.class, args);
      }
     
}
