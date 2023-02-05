package com.example.demo.edu.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("service-order")
public interface OrderClient {

    @GetMapping("isBuyCourse/{courseId}/{memberId}")
    public boolean isbuy(@PathVariable("courseId") String courseId, @PathVariable("memberId") String memberId);
}
