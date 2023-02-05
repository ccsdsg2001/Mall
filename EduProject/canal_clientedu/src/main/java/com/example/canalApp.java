package com.example;

import com.example.client.CanalClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

/**
 * @author cc
 * @date 2023年02月02日 22:24
 */
@SpringBootApplication
public class canalApp implements CommandLineRunner {

    @Resource
    private CanalClient canalClient;
     public static void main(String[] args) {
           SpringApplication.run(canalApp.class, args);
      }

    @Override
    public void run(String... strings) throws Exception {
        //项目启动，执行canal客户端监听
        canalClient.run();
    }
}
