package com.example.demo.edu.Client;

import com.example.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("service-ucenter")
public interface UcenterClient {


    @GetMapping("/educenter/member/conuntRegister/{day}")
    public R countRegister(@PathVariable String day);
}
